package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
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
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    @Transactional
    public void registerItem(ItemCommand.RegisterItemRequest command, UUID productId) {
        var initItem = itemMapper.toEntity(command);
        var item = itemStore.store(initItem);
        var product = productReader.getProduct(productId);
        item.changeProduct(product);
        itemOptionSeriesFactory.store(command, item);
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
