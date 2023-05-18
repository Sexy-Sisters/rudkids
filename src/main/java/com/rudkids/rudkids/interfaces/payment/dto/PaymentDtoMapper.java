package com.rudkids.rudkids.interfaces.payment.dto;

import com.rudkids.rudkids.domain.payment.PaymentCommand;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoMapper {

    public PaymentCommand.Confirm toCommand(PaymentRequest.Confirm request) {
        return PaymentCommand.Confirm.builder()
            .paymentKey(request.paymentKey())
            .orderId(request.orderId())
            .amount(request.amount())
            .build();
    }
}
