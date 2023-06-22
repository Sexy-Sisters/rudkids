package com.rudkids.api.payment;

import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public void paymentConfirm(@RequestBody PaymentRequest.Confirm request) {
        paymentService.validateAndConfirm(request);
    }
}
