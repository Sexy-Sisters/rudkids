package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
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
    public void registerItem(ItemCommand.RegisterItemRequest command, UUID productId) {
        var initItem = itemMapper.toEntity(command);
        var item = itemStore.store(initItem);
        var product = productReader.getProduct(productId);
        item.changeProduct(product);
        itemOptionSeriesFactory.store(command, item);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Detail findItemDetail(UUID id) {
        var item = itemReader.getItem(id);
        var itemOptionSeriesList = itemReader.getItemOptionSeries(item);
        return itemMapper.toDetail(item, itemOptionSeriesList);
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
