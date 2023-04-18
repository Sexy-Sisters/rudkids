package com.rudkids.rudkids.infrastructure.order;

import com.rudkids.rudkids.domain.order.domain.Order;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByUser(User user);
}
