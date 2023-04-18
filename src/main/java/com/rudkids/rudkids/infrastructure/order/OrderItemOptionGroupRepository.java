package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.domain.orderItem.OrderItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, UUID> {
}
