package com.rudkids.rudkids.interfaces.order.dto;

import com.rudkids.rudkids.domain.order.domain.OrderStatus;
import com.rudkids.rudkids.domain.order.domain.PayMethod;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class OrderRequest {


    @Builder
    public record Register(
        PayMethod payMethod,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage
    ) {
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
