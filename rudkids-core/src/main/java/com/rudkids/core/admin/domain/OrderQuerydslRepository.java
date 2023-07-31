package com.rudkids.core.admin.domain;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQuerydslRepository {
    Page<Order> getOrders(
        OrderStatus orderStatus,
        String deliveryTrackingNumber,
        String customerName,
        Pageable pageable
    );
}
