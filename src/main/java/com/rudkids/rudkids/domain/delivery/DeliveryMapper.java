package com.rudkids.rudkids.domain.delivery;

import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

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
