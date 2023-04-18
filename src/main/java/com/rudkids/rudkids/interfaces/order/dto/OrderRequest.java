package com.rudkids.rudkids.interfaces.order.dto;

import java.util.List;
import java.util.UUID;

public class OrderRequest {

    public record Register(
        String payMethod,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddress1,
        String receiverAddress2,
        String etcMessage,
        List<OrderRequest.RegisterOrderItem> orderItemList
    ) {
    }

    public record RegisterOrderItem(
        UUID itemId,
        Integer orderCount,
//        String itemToken,
//        String itemName,
//        Long itemPrice,
        List<OrderRequest.RegisterOrderItemOptionGroup> orderItemOptionGroupList
    ) {
    }

    public record RegisterOrderItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<OrderRequest.RegisterOrderItemOption> orderItemOptionList
    ) {
    }

    public record RegisterOrderItemOption(
        Integer ordering,
        String itemOptionName,
        Long itemOptionPrice
    ) {
    }
}
