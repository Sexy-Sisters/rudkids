package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
