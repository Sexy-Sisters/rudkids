package com.rudkids.rudkids.domain.delivery;

import lombok.Builder;

public class DeliveryInfo {

    @Builder
    public record Main(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {
    }
}
