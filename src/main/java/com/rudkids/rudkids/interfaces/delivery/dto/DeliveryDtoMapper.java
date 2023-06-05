package com.rudkids.rudkids.interfaces.delivery.dto;

import com.rudkids.rudkids.domain.delivery.DeliveryCommand;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDtoMapper {

    public DeliveryCommand.Create toCommand(DeliveryRequest.Create request) {
        return DeliveryCommand.Create.builder()
            .receiverName(request.receiverName())
            .receiverPhone(request.receiverPhone())
            .zipCode(request.zipCode())
            .address1(request.address1())
            .address2(request.address2())
            .message(request.message())
            .build();
    }

    public DeliveryCommand.Update toCommand(DeliveryRequest.Update request) {
        return DeliveryCommand.Update.builder()
            .receiverName(request.receiverName())
            .receiverPhone(request.receiverPhone())
            .zipCode(request.zipCode())
            .address1(request.address1())
            .address2(request.address2())
            .message(request.message())
            .build();
    }
}
