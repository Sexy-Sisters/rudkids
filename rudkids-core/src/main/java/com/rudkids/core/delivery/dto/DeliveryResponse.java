package com.rudkids.core.delivery.dto;

import com.rudkids.core.delivery.domain.Delivery;
import lombok.Builder;

import java.util.UUID;

public class DeliveryResponse {

    @Builder
    public record Info(
        UUID deliveryId,
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address,
        String extraAddress,
        String message,
        boolean isBasic
    ) {
        public Info(Delivery delivery) {
            this(
                delivery.getId(),
                delivery.getReceiverName(),
                delivery.getReceiverPhone(),
                delivery.getZipCode(),
                delivery.getAddress1(),
                delivery.getAddress2(),
                delivery.getMessage(),
                delivery.isBasic()
            );
        }
    }
}
