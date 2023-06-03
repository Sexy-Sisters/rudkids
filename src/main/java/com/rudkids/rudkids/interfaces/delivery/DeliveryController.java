package com.rudkids.rudkids.interfaces.delivery;

import com.rudkids.rudkids.domain.delivery.service.DeliveryService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.delivery.dto.DeliveryDtoMapper;
import com.rudkids.rudkids.interfaces.delivery.dto.DeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryDtoMapper deliveryDtoMapper;

    @PostMapping
    public void create(@AuthenticationPrincipal AuthUser.Login loginUser, @RequestBody DeliveryRequest.Create request) {
        var command = deliveryDtoMapper.toCommand(request);
        deliveryService.create(loginUser.id(), command);
    }

    @GetMapping
    public ResponseEntity findAll(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = deliveryService.findAll(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@AuthenticationPrincipal AuthUser.Login loginUser, @PathVariable("id")UUID deliveryId) {
        var response = deliveryService.find(deliveryId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public void changeStatus(@AuthenticationPrincipal AuthUser.Login loginUser, @PathVariable("id")UUID deliveryId) {
        deliveryService.changeStatus(loginUser.id(), deliveryId);
    }

    @PutMapping("/{id}")
    public void update(@AuthenticationPrincipal AuthUser.Login loginUser,
                       @PathVariable("id")UUID deliveryId,
                       @RequestBody DeliveryRequest.Update request) {
        var command = deliveryDtoMapper.toCommand(request);
        deliveryService.update(loginUser.id(), deliveryId, command);
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal AuthUser.Login loginUser, @PathVariable("id")UUID deliveryId) {
        deliveryService.delete(loginUser.id(), deliveryId);
    }
}