package com.rudkids.rudkids.interfaces.order;

import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.order.dto.OrderDtoMapper;
import com.rudkids.rudkids.interfaces.order.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    @PostMapping
    public void create(
        @AuthenticationPrincipal AuthUser.Login  loginUser,
        @RequestBody OrderRequest.Register request
    ) {
        var command = orderDtoMapper.toCommand(request);
        orderService.create(command, loginUser.id());
    }
}
