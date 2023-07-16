package com.rudkids.core.order.infrastructure;

import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.domain.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<Order, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("SELECT o.delivery FROM Order o WHERE o.delivery.deliveryStatus = 'ING'")
    List<OrderDelivery> findOrdersWithDeliveryStatusING();
}
