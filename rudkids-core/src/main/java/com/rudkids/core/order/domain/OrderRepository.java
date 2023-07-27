package com.rudkids.core.order.domain;

import com.rudkids.core.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
    Order get(UUID id);
    List<Order> getOrders(User user);
    void delete(Order order);
}