package com.rudkids.rudkids.interfaces.delivery.dto;

public class DeliveryRequest {

    public record Create(
        String receiverName,
        String receiverPhone,
        String zipCode,
        String address1,
        String address2,
        String message
    ) {
    }

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
