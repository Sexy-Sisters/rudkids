package com.rudkids.rudkids.interfaces.order.dto;

import com.rudkids.rudkids.domain.order.OrderCommand;
import com.rudkids.rudkids.domain.order.OrderInfo;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {

    public OrderCommand.CreateRequest toCommand(OrderRequest.Register request) {
        return OrderCommand.CreateRequest.builder()
            .payMethod(request.payMethod())
            .build();
    }

    public OrderCommand.UpdateDeliveryFragment toCommand(OrderRequest.UpdateDeliveryFragment request) {
        return OrderCommand.UpdateDeliveryFragment.builder()
            .receiverName(request.receiverName())
            .receiverPhone(request.receiverPhone())
            .receiverZipcode(request.receiverZipcode())
            .receiverAddress1(request.receiverAddress1())
            .receiverAddress2(request.receiverAddress2())
            .etcMessage(request.etcMessage())
            .build();
    }

    public OrderResponse.Main toResponse(OrderInfo.Main info) {
        return new OrderResponse.Main(info.orderId(), info.createdAt());
    }
}
