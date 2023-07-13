package com.rudkids.core.delivery.service;

import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.dto.DeliveryResponse;

import java.util.List;
import java.util.UUID;

public interface DeliveryService {

    UUID create(UUID userId, DeliveryRequest.Create request);
    List<DeliveryResponse.Info> getAll(UUID userId);
    DeliveryResponse.Info get(UUID userId, UUID deliveryId);
    void changeStatus(UUID userId, UUID deliveryId);
    void update(UUID userId, UUID deliveryId, DeliveryRequest.Update request);
    void delete(UUID userId, UUID deliveryId);
}
