package com.rudkids.api.delivery;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Void> create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody DeliveryRequest.Create request
    ) {
        deliveryService.create(loginUser.id(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAll(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = deliveryService.getAll(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID deliveryId
    ) {
        var response = deliveryService.get(loginUser.id(), deliveryId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> changeStatus(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id")UUID deliveryId
    ) {
        deliveryService.changeStatus(loginUser.id(), deliveryId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public void update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id")UUID deliveryId,
        @RequestBody DeliveryRequest.Update request
    ) {
        deliveryService.update(loginUser.id(), deliveryId, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id")UUID deliveryId
    ) {
        deliveryService.delete(loginUser.id(), deliveryId);
    }
}
