package com.rudkids.rudkids.infrastructure.delivery;

import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    List<Delivery> findByUserId(UUID userId);
}
