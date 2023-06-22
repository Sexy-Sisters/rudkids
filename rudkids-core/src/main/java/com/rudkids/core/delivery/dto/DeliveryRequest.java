package com.rudkids.core.delivery.dto;

import com.rudkids.core.delivery.domain.Address;
import com.rudkids.core.delivery.domain.Delivery;

public class DeliveryRequest {

    public record Create(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {
        public Delivery toEntity() {
            var address = Address.create(address1, address2, zipCode);
            return Delivery.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .address(address)
                .message(message)
                .build();
        }
    }

    public record Update(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {}
}
