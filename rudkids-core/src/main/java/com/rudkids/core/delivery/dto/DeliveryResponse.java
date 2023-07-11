package com.rudkids.core.delivery.dto;

import com.rudkids.core.delivery.domain.Delivery;
import lombok.Builder;

public class DeliveryResponse {

    @Builder
    public record Info(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message,
        boolean isBasic
    ) {
        public Info(Delivery delivery) {
            this(
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
