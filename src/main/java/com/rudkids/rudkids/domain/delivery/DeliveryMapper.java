package com.rudkids.rudkids.domain.delivery;

import com.rudkids.rudkids.domain.delivery.domain.Address;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    public Delivery toEntity(User user, DeliveryCommand.Create command) {
        var address = Address.create(command.address1(), command.address2(), command.zipCode());
        return Delivery.builder()
            .user(user)
            .receiverName(command.receiverName())
            .receiverPhone(command.receiverPhone())
            .address(address)
            .message(command.message())
            .build();
    }

    public DeliveryInfo.Main toInfo(Delivery delivery) {
        return DeliveryInfo.Main.builder()
            .receiverName(delivery.getReceiverName())
            .receiverPhone(delivery.getReceiverPhone())
            .zipCode(delivery.getZipCode())
            .address1(delivery.getAddress1())
            .address2(delivery.getAddress2())
            .message(delivery.getMessage())
            .build();
    }
}
