package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemMapper itemMapper;
    private final ProductReader productReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    @Transactional
    public void registerItem(ItemCommand.RegisterRequest command) {
        var name = Name.create(command.name());
        var itemBio = ItemBio.create(command.itemBio());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());

        var initItem = Item.builder()
            .name(name)
            .itemBio(itemBio)
            .price(price)
            .quantity(quantity)
            .limitType(command.limitType())
            .build();

        var product = productReader.getProduct(command.productId());
        initItem.changeProduct(product);

        itemStore.store(initItem);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Detail findItemDetail(UUID id) {
        var item = itemReader.getItem(id);
        var itemOptionSeriesList = itemReader.getItemOptionSeries(item);
        return itemMapper.toDetail(item, itemOptionSeriesList);
    }

    @Override
    public String changeOnSales(UUID id) {
        var item = itemReader.getItem(id);
        item.changeOnSales();
        return item.getItemStatus().name();
    }

    @Override
    public String changeEndOfSales(UUID id) {
        var item = itemReader.getItem(id);
        item.changeEndOfSales();
        return item.getItemStatus().name();
    }

    @Override
    public String changePrepare(UUID id) {
        var item = itemReader.getItem(id);
        item.changePrepare();
        return item.getItemStatus().name();
    }
}
