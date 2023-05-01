package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;

public interface OrderStore {
    Order store(Order order);

    void delete(Order order);
}
