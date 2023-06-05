package com.rudkids.rudkids.infrastructure.delivery;

import com.rudkids.rudkids.domain.delivery.DeliveryStore;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStoreImpl implements DeliveryStore {
    private final DeliveryRepository deliveryRepository;

    @Override
    public void store(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public void delete(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }
}
