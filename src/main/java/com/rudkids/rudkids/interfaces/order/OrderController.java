package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.domain.order.OrderInfo;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.order.dto.OrderDtoMapper;
import com.rudkids.rudkids.interfaces.order.dto.OrderRequest;
import com.rudkids.rudkids.interfaces.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    @PostMapping
    public void create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody OrderRequest.Register request
    ) {
        var command = orderDtoMapper.toCommand(request);
        orderService.create(command, loginUser.id());
    }

    @GetMapping("/{id}")
    public OrderInfo.Detail find(@PathVariable(name = "id") UUID orderId) {
        return orderService.find(orderId);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse.Main>> findAllMine(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var responseList = orderService.findAllMine(loginUser.id()).stream()
            .map(orderDtoMapper::toResponse)
            .toList();
        return ResponseEntity.ok(responseList);
    }

    @PatchMapping("/{id}")
    public void updateDeliveryFragment(
        @PathVariable(name = "id") UUID orderId,
        @RequestBody OrderRequest.UpdateDeliveryFragment request
    ) {
        var command = orderDtoMapper.toCommand(request);
        orderService.updateDeliveryFragment(command, orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") UUID orderId) {
        orderService.delete(orderId);
    }
}

