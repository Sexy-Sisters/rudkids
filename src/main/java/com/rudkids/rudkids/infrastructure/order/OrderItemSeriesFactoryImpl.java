package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderItemSeriesFactory;
import com.rudkids.rudkids.domain.order.OrderStore;
import com.rudkids.rudkids.domain.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {
    private final OrderStore orderStore;
    private final ItemReader itemReader;

    @Override
    public void store(Order order, OrderCommand.Register command) {
        command.orderItemList().forEach(ItemRequest -> {
            var item = itemReader.getItem(ItemRequest.itemId());
            var initItem = ItemRequest.toEntity(order, item);
            order.addOrderItem(initItem);
            var orderItem = orderStore.store(initItem);

            ItemRequest.orderItemOptionGroupList().forEach(optionGroupRequest -> {
                var initOptionGroup = optionGroupRequest.toEntity(orderItem);
                orderItem.addOrderItemOptionGroup(initOptionGroup);
                var optionGroup = orderStore.store(initOptionGroup);

                var initOption =  optionGroupRequest.orderItemOption().toEntity(optionGroup);
                optionGroup.addOrderItemOption(initOption);
                orderStore.store(initOption);
            });
        });
    }
}
