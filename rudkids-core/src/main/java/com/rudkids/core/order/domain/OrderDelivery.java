package com.rudkids.core.order.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDelivery {

    private String receiverName;

    private String receiverPhone;

    private String receivedAddress;

    private String message;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private OrderDeliveryStatus deliveryStatus = OrderDeliveryStatus.READY;

    @Builder
    public OrderDelivery(String receiverName, String receiverPhone, String receivedAddress, String message) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receivedAddress = receivedAddress;
        this.message = message;
    }

    public void registerTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        deliveryStatus = OrderDeliveryStatus.ING;
    }

    public boolean isCompleted() {
        return deliveryStatus == OrderDeliveryStatus.COMP;
    }

    public void changeStatusToComp() {
        deliveryStatus = OrderDeliveryStatus.COMP;
    }
}
