package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;

import java.util.UUID;

public interface OrderStore {
    Order store(Order order);

    void delete(UUID orderId);
}
