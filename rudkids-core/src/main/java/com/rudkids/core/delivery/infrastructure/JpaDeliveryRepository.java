package com.rudkids.core.delivery.infrastructure;

import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaDeliveryRepository extends JpaRepository<Delivery, UUID> {
    List<Delivery> findByUserId(UUID userId);
    Delivery findByUserAndBasicTrue(User user);
}
