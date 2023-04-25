package com.rudkids.rudkids.interfaces.order.dto;

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
        String etcMessage,
        List<OrderRequest.RegisterOrderItem> orderItemList
    ) {
    }

    @Builder
    public record RegisterOrderItem(
        UUID itemId,
        Integer orderCount,
        List<OrderRequest.RegisterOrderItemOptionGroup> orderItemOptionGroupList
    ) {
    }

    @Builder
    public record RegisterOrderItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<OrderRequest.RegisterOrderItemOption> orderItemOptionList
    ) {
    }

    @Builder
    public record RegisterOrderItemOption(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {
    }
}
