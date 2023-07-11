package com.rudkids.core.delivery.service;

import com.rudkids.core.delivery.domain.Address;
import com.rudkids.core.delivery.domain.DeliveryRepository;
import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.dto.DeliveryResponse;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public void create(UUID userId, DeliveryRequest.Create request) {
        var user = userRepository.getUser(userId);
        var delivery = request.toEntity();
        delivery.registerUser(user);
        deliveryRepository.save(delivery);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryResponse.Info> getAll(UUID userId) {
        return deliveryRepository.getDeliveries(userId).stream()
            .map(DeliveryResponse.Info::new)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DeliveryResponse.Info get(UUID userId, UUID deliveryId) {
        var user = userRepository.getUser(userId);
        var delivery = deliveryRepository.get(deliveryId);
        delivery.validateHasSameUser(user);
        return new DeliveryResponse.Info(delivery);
    }

    @Override
    public void changeStatus(UUID userId, UUID deliveryId) {
        var user = userRepository.getUser(userId);
        var delivery = deliveryRepository.get(deliveryId);
        delivery.validateHasSameUser(user);
        delivery.changeBasic();
    }

    @Override
    public void update(UUID userId, UUID deliveryId, DeliveryRequest.Update command) {
        var user = userRepository.getUser(userId);
        var delivery = deliveryRepository.get(deliveryId);
        delivery.validateHasSameUser(user);

        var address = Address.create(command.address(), command.extraAddress(), command.zipCode());
        delivery.update(command.receiverName(), command.receiverPhone(), address, command.message());
    }

    @Override
    public void delete(UUID userId, UUID deliveryId) {
        var user = userRepository.getUser(userId);
        var delivery = deliveryRepository.get(deliveryId);
        delivery.validateHasSameUser(user);
        deliveryRepository.delete(delivery);
    }
}
