package com.rudkids.rudkids.domain.order.service.strategy.orderStatus;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.order.domain.OrderStatus;

public interface OrderStatusChangeStrategy {
    boolean isOrderStrategy(OrderStatus orderStatus);
    void changeStatus(Order order);
}
