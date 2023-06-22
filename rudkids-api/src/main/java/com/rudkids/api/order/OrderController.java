package com.rudkids.api.order;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody OrderRequest.Create request
    ) {
        orderService.create(loginUser.id(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable(name = "id") UUID orderId) {
        var response = orderService.get(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getAllMine(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PageableDefault Pageable pageable
    ) {
        var response = orderService.getAllMine(loginUser.id(), pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") UUID orderId) {
        orderService.delete(orderId);
    }
}
