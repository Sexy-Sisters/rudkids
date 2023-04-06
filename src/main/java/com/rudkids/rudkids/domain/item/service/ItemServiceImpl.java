package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupName;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupStore;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOptionName;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOptionPrice;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOptionStore;
import com.rudkids.rudkids.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemMapper itemMapper;
    private final ProductReader productReader;
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    @Transactional
    public void registerItem(ItemCommand.RegisterItemRequest command) {
        var initItem = itemMapper.toEntity(command);
        var item = itemStore.store(initItem);

        var product = productReader.getProduct(command.productId());
        initItem.changeProduct(product);

        command.itemOptionGroupList().forEach(itemOptionGroupRequest -> {
            var itemOptionGroupOrdering = itemOptionGroupRequest.ordering();
            var itemOptionGroupName = ItemOptionGroupName.create(itemOptionGroupRequest.itemOptionGroupName());

            var initItemOptionGroup = ItemOptionGroup.builder()
                .item(item)
                .ordering(itemOptionGroupOrdering)
                .itemOptionGroupName(itemOptionGroupName)
                .build();
            var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

            itemOptionGroupRequest.itemOptionList().forEach(itemOptionRequest -> {
                var itemOptionOrdering = itemOptionRequest.ordering();
                var itemOptionName = ItemOptionName.create(itemOptionRequest.itemOptionName());
                var itemOptionPrice = ItemOptionPrice.create(itemOptionRequest.itemOptionPrice());

                var initItemOption = ItemOption.builder()
                    .itemOptionGroup(itemOptionGroup)
                    .ordering(itemOptionOrdering)
                    .itemOptionName(itemOptionName)
                    .itemOptionPrice(itemOptionPrice)
                    .build();
                var itemOption = itemOptionStore.store(initItemOption);
            });
        });
    }

    @Override
    public List<ItemInfo.Main> findItems(UUID productId) {
        var product = productReader.getProduct(productId);
        return product.getItems().stream()
            .map(itemMapper::toMain)
            .toList();
    }

    @Override
    public ItemInfo.Detail findItemDetail(UUID id) {
        var item = itemReader.getItem(id);
        return itemMapper.toDetail(item);
    }

    @Override
    public String openItem(UUID id) {
        var item = itemReader.getItem(id);
        item.changeOnSales();
        return item.getItemStatus().name();
    }

    @Override
    public String closeItem(UUID id) {
        var item = itemReader.getItem(id);
        item.changeEndOfSales();
        return item.getItemStatus().name();
    }
}
