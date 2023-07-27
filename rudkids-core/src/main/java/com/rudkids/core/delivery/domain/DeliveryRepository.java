package com.rudkids.core.delivery.domain;

import java.util.List;
import java.util.UUID;

public interface DeliveryRepository {
    void save(Delivery delivery);
    List<Delivery> getDeliveries(UUID userId);
    Delivery get(UUID id);
    Delivery getBasic();
    void delete(Delivery delivery);
}
