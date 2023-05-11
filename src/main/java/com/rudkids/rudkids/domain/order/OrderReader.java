package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderReader {
    Order getOrder(UUID orderId);

    Page<Order> getOrders(Pageable pageable);
}
