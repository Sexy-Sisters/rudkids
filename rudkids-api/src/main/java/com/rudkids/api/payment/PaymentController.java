package com.rudkids.api.payment;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity getInformation(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID orderId
    ) {
        var response = paymentService.getInformation(loginUser.id(), orderId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> confirm(@RequestBody PaymentRequest.Confirm request) {
        paymentService.confirm(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(
        @PathVariable("id") UUID orderId,
        @RequestBody PaymentRequest.Cancel request
    ) {
        paymentService.cancel(orderId, request);
        return ResponseEntity.ok().build();
    }
}
