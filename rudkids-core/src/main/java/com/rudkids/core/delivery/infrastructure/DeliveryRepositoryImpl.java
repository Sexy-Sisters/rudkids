package com.rudkids.core.delivery.infrastructure;

import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.delivery.exception.DeliveryNotFoundException;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {
    private final JpaDeliveryRepository deliveryRepository;

    @Override
    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getDeliveries(UUID userId) {
        return deliveryRepository.findByUserId(userId);
    }

    @Override
    public Delivery get(UUID id) {
        return deliveryRepository.findById(id)
            .orElseThrow(DeliveryNotFoundException::new);
    }

    @Override
    public Delivery getBasic(User user) {
        return deliveryRepository.findByUserAndBasicTrue(user);
    }

    @Override
    public void delete(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }
}
