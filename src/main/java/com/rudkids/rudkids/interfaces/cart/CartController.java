package com.rudkids.rudkids.interfaces.cart;

import com.rudkids.rudkids.domain.cart.application.CartService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.cart.dto.CartDtoMapper;
import com.rudkids.rudkids.interfaces.cart.dto.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;

    @PostMapping
    public void addCartItem(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @RequestBody CartRequest.AddCartItem request
    ) {
        var command = cartDtoMapper.to(request);
        cartService.addCartItem(loginUser.id(), command);
    }
}
