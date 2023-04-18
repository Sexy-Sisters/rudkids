package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;

public interface OrderItemSeriesFactory {
    void store(Order order, OrderCommand.Register command);
}
