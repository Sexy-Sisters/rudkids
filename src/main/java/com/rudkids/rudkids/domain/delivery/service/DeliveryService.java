package com.rudkids.rudkids.domain.delivery.service;

import com.rudkids.rudkids.domain.delivery.*;
import com.rudkids.rudkids.domain.delivery.domain.Address;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService {
    private final UserReader userReader;
    private final DeliveryReader deliveryReader;
    private final DeliveryStore deliveryStore;
    private final DeliveryMapper deliveryMapper;

    public void create(UUID userId, DeliveryCommand.Create command) {
        var user = userReader.getUser(userId);
        var address = Address.create(command.address1(), command.address2(), command.zipCode());
        var delivery = Delivery.builder()
            .user(user)
            .receiverName(command.receiverName())
            .receiverPhone(command.receiverPhone())
            .address(address)
            .message(command.message())
            .build();
        user.addDeliveryAddress(delivery);

        deliveryStore.store(delivery);
    }

    @Transactional(readOnly = true)
    public List<DeliveryInfo.Main> findAll(UUID userId) {
        return deliveryReader.getDeliveries(userId).stream()
            .map(deliveryMapper::toInfo)
            .toList();
    }

    @Transactional(readOnly = true)
    public DeliveryInfo.Main find(UUID deliveryId) {
        var delivery = deliveryReader.get(deliveryId);
        return deliveryMapper.toInfo(delivery);
    }

    public void changeStatus(UUID userId, UUID deliveryId) {
        var user = userReader.getUser(userId);
        var delivery = deliveryReader.get(deliveryId);
        delivery.validateHasSameUser(user);
        delivery.changeBasic();
    }

    public void update(UUID userId, UUID deliveryId, DeliveryCommand.Update command) {
        var user = userReader.getUser(userId);
        var delivery = deliveryReader.get(deliveryId);
        delivery.validateHasSameUser(user);
        var address = Address.create(command.address1(), command.address2(), command.zipCode());
        delivery.update(command.receiverName(), command.receiverPhone(), address, command.message());
    }

    public void delete(UUID userId, UUID deliveryId) {
        var user = userReader.getUser(userId);
        var delivery = deliveryReader.get(deliveryId);
        delivery.validateHasSameUser(user);
        deliveryStore.delete(delivery);
    }
}
