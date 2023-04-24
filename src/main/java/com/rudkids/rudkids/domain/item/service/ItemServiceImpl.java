package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.item.exception.ItemStatusNotFoundException;
import com.rudkids.rudkids.domain.item.service.strategy.itemStatus.ItemStatusChangeStrategy;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
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
    private final ProductReader productReader;
    private final UserReader userReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;
    private final List<ItemStatusChangeStrategy> itemStatusChangeStrategyList;

    @Override
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
    public ItemInfo.Detail find(UUID itemId) {
        var item = itemReader.getItem(itemId);
        var itemOptionSeriesList = itemReader.getItemOptionSeries(item);
        return itemMapper.toDetail(item, itemOptionSeriesList);
    }

    @Override
    public ItemStatus changeItemStatus(UUID itemId, ItemStatus itemStatus) {
        // TODO:: 권한 검사
        var strategy = findStrategy(itemStatus);
        var item = itemReader.getItem(itemId);
        strategy.changeStatus(item);
        return item.getItemStatus();
    }

    private ItemStatusChangeStrategy findStrategy(ItemStatus itemStatus) {
        return itemStatusChangeStrategyList.stream()
            .filter(strategy -> strategy.isItemStatus(itemStatus))
            .findFirst()
            .orElseThrow(ItemStatusNotFoundException::new);
    }
}
