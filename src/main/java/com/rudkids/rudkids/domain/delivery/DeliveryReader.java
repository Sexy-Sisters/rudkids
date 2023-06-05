package com.rudkids.rudkids.domain.delivery;

import com.rudkids.rudkids.domain.delivery.domain.Delivery;

import java.util.List;
import java.util.UUID;

public interface DeliveryReader {
    List<Delivery> getDeliveries(UUID userId);
    Delivery get(UUID deliveryId);
}
