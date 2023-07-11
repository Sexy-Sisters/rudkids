package com.rudkids.core.delivery.dto;

import com.rudkids.core.delivery.domain.Address;
import com.rudkids.core.delivery.domain.Delivery;

public class DeliveryRequest {

    public record Create(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address,
        String extraAddress,
        String message
    ) {
        public Delivery toEntity() {
            var generatedAddress = Address.create(address, extraAddress, zipCode);
            return Delivery.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .address(generatedAddress)
                .message(message)
                .build();
        }
    }

    public record Update(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address,
        String extraAddress,
        String message
    ) {}
}
