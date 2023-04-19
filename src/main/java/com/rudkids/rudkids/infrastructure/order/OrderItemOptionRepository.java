package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, UUID> {
}
