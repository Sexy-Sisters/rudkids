package com.rudkids.core.order.domain;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
    Order get(UUID id);
    List<Order> getOrders();
    void delete(Order order);
}