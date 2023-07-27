package com.rudkids.core.order.infrastructure;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserOrderByCreatedAtAsc(User user);
}
