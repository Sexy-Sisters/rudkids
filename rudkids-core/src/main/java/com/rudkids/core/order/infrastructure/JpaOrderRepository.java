package com.rudkids.core.order.infrastructure;

import com.rudkids.core.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<Order, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Order o WHERE o.orderStatus = 'ORDERING'")
    void deleteNotOrderCompleted();
}
