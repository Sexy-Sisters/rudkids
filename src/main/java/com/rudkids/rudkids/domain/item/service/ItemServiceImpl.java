package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.user.UserReader;
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
    private final UserReader userReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    @Transactional
    public void create(ItemCommand.RegisterItemRequest command, UUID productId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminOrPartnerRole();

        var name = Name.create(command.name());
        var itemBio = ItemBio.create(command.itemBio());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());
        var limitType = command.limitType();

        var initItem = Item.create(name, itemBio, price, quantity, limitType);
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        var product = productReader.getProduct(productId);
        initItem.setProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Detail find(UUID id) {
        var item = itemReader.getItem(id);
        var itemOptionSeriesList = itemReader.getItemOptionSeries(item);
        return itemMapper.toDetail(item, itemOptionSeriesList);
    }

    @Override
    public ItemStatus changeOnSales(UUID id) {
        var item = itemReader.getItem(id);
        item.changeOnSales();
        return item.getItemStatus();
    }

    @Override
    public ItemStatus changeEndOfSales(UUID id) {
        var item = itemReader.getItem(id);
        item.changeEndOfSales();
        return item.getItemStatus();
    }

    @Override
    public ItemStatus changePrepare(UUID id) {
        var item = itemReader.getItem(id);
        item.changePrepare();
        return item.getItemStatus();
    }
}
