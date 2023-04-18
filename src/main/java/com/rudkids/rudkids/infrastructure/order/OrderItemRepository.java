package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
