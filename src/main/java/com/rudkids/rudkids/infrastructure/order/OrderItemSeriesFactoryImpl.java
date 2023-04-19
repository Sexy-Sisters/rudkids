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
        command.orderItemList().forEach(registerOrderItem -> {
            var item = itemReader.getItem(registerOrderItem.itemId());
            var initOrderItem = registerOrderItem.toEntity(order, item);
            order.addOrderItem(initOrderItem);
            var orderItem = orderStore.store(initOrderItem);

            registerOrderItem.orderItemOptionGroupList().forEach(registerOrderItemOptionGroup -> {
                var initOrderItemOptionGroup = registerOrderItemOptionGroup.toEntity(orderItem);
                orderItem.addOrderItemOptionGroup(initOrderItemOptionGroup);
                var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                registerOrderItemOptionGroup.orderItemOptionList().forEach(registerOrderItemOption -> {
                    var initOrderItemOption = registerOrderItemOption.toEntity(orderItemOptionGroup);
                    orderItemOptionGroup.addOrderItemOption(initOrderItemOption);
                    orderStore.store(initOrderItemOption);
                });
            });
        });
    }
}
