package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItem;
import com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption.OrderItemOption;
import com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOptionGroup.OrderItemOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderItem store(OrderItem orderItem);
    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
    OrderItemOption store(OrderItemOption orderItemOption);
}
