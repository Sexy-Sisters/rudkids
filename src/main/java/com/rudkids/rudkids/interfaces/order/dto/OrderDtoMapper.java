package com.rudkids.rudkids.interfaces.order.dto;

import com.rudkids.rudkids.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {

    public OrderCommand.CreateRequest toCommand(OrderRequest.Register request) {
        return OrderCommand.CreateRequest.builder()
            .receiverName(request.receiverName())
            .receiverPhone(request.receiverPhone())
            .receiverZipcode(request.receiverZipcode())
            .receiverAddress1(request.receiverAddress1())
            .receiverAddress2(request.receiverAddress2())
            .etcMessage(request.etcMessage())
            .payMethod(request.payMethod())
            .build();
    }
}
