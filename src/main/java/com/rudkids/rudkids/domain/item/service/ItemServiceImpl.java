package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.item.exception.ItemStatusNotFoundException;
import com.rudkids.rudkids.domain.item.service.strategy.itemStatus.ItemStatusChangeStrategy;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemMapper itemMapper;
    private final ItemFactory itemFactory;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;
    private final ProductReader productReader;
    private final UserReader userReader;
    private final List<ItemStatusChangeStrategy> itemStatusChangeStrategyList;

    @Override
    public void create(ItemCommand.CreateItemRequest command, UUID productId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminOrPartnerRole();
        var initItem = itemFactory.create(command);
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        var product = productReader.getProduct(productId);
        initItem.setProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Detail find(UUID itemId) {
        var item = itemReader.getItem(itemId);
        var itemOptionSeriesList = itemReader.getItemOptionSeries(item);
        return itemMapper.toDetail(item, itemOptionSeriesList);
    }

    @Override
    public void update(ItemCommand.UpdateRequest command, UUID itemId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemReader.getItem(itemId);
        itemFactory.update(item, command);
    }

    @Override
    public ItemStatus changeItemStatus(UUID itemId, ItemStatus itemStatus, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminOrPartnerRole();

        var strategy = findStrategy(itemStatus);
        var item = itemReader.getItem(itemId);
        strategy.changeStatus(item);
        return item.getItemStatus();
    }

    @Override
    public void delete(UUID itemId, UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminOrPartnerRole();
        var item = itemReader.getItem(itemId);
        itemStore.delete(item);
    }

    private ItemStatusChangeStrategy findStrategy(ItemStatus itemStatus) {
        return itemStatusChangeStrategyList.stream()
            .filter(strategy -> strategy.isItemStatus(itemStatus))
            .findFirst()
            .orElseThrow(ItemStatusNotFoundException::new);
    }
}
