package com.rudkids.api.order;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/payment/widget")
    public ResponseEntity getPaymentWidgetInfo(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = orderService.getPaymentWidgetInfo(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity order(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody OrderRequest.OrderAndPayment request
    ) {
        var response = orderService.order(loginUser.id(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable(name = "id") UUID orderId) {
        var response = orderService.get(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getAll(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = orderService.getAll(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity cancel(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID orderId,
        @RequestBody OrderRequest.PaymentCancel request
    ) {
        var response = orderService.cancel(loginUser.id(), orderId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancel")
    public ResponseEntity getCancelOrders(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = orderService.getCancelOrders(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable(name = "id") UUID orderId
    ) {
        orderService.delete(loginUser.id(), orderId);
    }
}
