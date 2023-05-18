package com.rudkids.rudkids.interfaces.payment;

import com.rudkids.rudkids.domain.payment.service.PaymentService;
import com.rudkids.rudkids.interfaces.payment.dto.PaymentDtoMapper;
import com.rudkids.rudkids.interfaces.payment.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentDtoMapper paymentDtoMapper;

    @PostMapping("/confirm")
    public void paymentConfirm(@RequestBody PaymentRequest.Confirm request) {
        var command = paymentDtoMapper.toCommand(request);
        paymentService.validateAndConfirm(command);
    }
}