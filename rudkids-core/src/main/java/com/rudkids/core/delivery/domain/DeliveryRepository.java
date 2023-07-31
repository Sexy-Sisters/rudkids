package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface DeliveryRepository {
    void save(Delivery delivery);
    List<Delivery> getDeliveries(UUID userId);
    Delivery get(UUID id);
    Delivery getBasic(User user);
    void delete(Delivery delivery);
}
