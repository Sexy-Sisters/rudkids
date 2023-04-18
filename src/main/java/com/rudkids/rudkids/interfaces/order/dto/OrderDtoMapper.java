package com.rudkids.rudkids.interfaces.order.dto;

import com.rudkids.rudkids.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoMapper {

    public OrderCommand.Register toCommand(OrderRequest.Register request) {
        List<OrderCommand.RegisterOrderItem> orderItemCommandList = request.orderItemList().stream()
            .map(this::toCommand)
            .toList();
        return OrderCommand.Register.builder()
            .receiverName(request.receiverName())
            .receiverPhone(request.receiverPhone())
            .receiverZipcode(request.receiverZipcode())
            .receiverAddress1(request.receiverAddress1())
            .receiverAddress2(request.receiverAddress2())
            .etcMessage(request.etcMessage())
            .payMethod(request.payMethod())
            .orderItemList(orderItemCommandList)
            .build();
    }

    private OrderCommand.RegisterOrderItem toCommand(OrderRequest.RegisterOrderItem request) {
        var orderItemOptionGroupList = request.orderItemOptionGroupList().stream()
            .map(this::toCommand)
            .toList();
        return OrderCommand.RegisterOrderItem.builder()
            .itemId(request.itemId())
            .orderCount(request.orderCount())
//            .itemName(request.itemName())
//            .itemPrice(request.itemPrice())
            .orderItemOptionGroupList(orderItemOptionGroupList)
            .build();
    }

    private OrderCommand.RegisterOrderItemOptionGroup toCommand(OrderRequest.RegisterOrderItemOptionGroup request) {
        var orderItemOptionList = request.orderItemOptionList().stream()
            .map(this::toCommand)
            .toList();
        return OrderCommand.RegisterOrderItemOptionGroup.builder()
            .ordering(request.ordering())
            .itemOptionGroupName(request.itemOptionGroupName())
            .orderItemOptionList(orderItemOptionList)
            .build();
    }

    private OrderCommand.RegisterOrderItemOption toCommand(OrderRequest.RegisterOrderItemOption request) {
        return OrderCommand.RegisterOrderItemOption.builder()
            .ordering(request.ordering())
            .itemOptionName(request.itemOptionName())
            .itemOptionPrice(request.itemOptionPrice())
            .build();
    }
}
