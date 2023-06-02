package com.rudkids.rudkids.infrastructure.delivery;

import com.rudkids.rudkids.domain.delivery.DeliveryReader;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.delivery.exception.DeliveryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryReaderImpl implements DeliveryReader {
    private final DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getDeliveries(UUID userId) {
        return deliveryRepository.findByUserId(userId);
    }

    @Override
    public Delivery get(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(DeliveryNotFoundException::new);
    }
}
