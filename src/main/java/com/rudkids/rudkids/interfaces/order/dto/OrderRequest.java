package com.rudkids.rudkids.interfaces.order.dto;

import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import lombok.Builder;

public class OrderRequest {

    @Builder
    public record Register(PayMethod payMethod) {
    }

    @Builder
    public record UpdateDeliveryFragment(
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage
    ) {
    }

    public record ChangeStatus(OrderStatus orderStatus) {
    }
}
