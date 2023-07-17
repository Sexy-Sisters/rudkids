package com.rudkids.core.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
    Order get(UUID id);
    List<OrderDelivery> getStatusIngOrderDeliveries();
    Page<Order> getOrders(Pageable pageable);
    void delete(Order order);
}