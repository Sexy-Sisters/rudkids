package com.rudkids.rudkids.domain.delivery;

import lombok.Builder;

public class DeliveryCommand {

    @Builder
    public record Create(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {
    }

    @Builder
    public record Update(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {
    }
}
