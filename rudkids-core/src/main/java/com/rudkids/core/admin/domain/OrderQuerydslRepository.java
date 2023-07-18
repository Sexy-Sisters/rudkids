package com.rudkids.core.admin.domain;

import com.rudkids.core.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQuerydslRepository {
    Page<Order> getOrders(String deliveryStatus, String orderStatus, String deliveryTrackingNumber, String customerName, Pageable pageable);
}
