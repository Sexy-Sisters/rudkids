package com.rudkids.rudkids.domain.order;

import com.rudkids.rudkids.domain.order.domain.PayMethod;
import lombok.Builder;

public class OrderCommand {

    @Builder
    public record CreateRequest(
        PayMethod payMethod,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage
    ) {
    }
}
