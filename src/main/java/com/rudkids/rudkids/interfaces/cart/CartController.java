package com.rudkids.rudkids.interfaces.cart;

import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.cart.dto.CartDtoMapper;
import com.rudkids.rudkids.interfaces.cart.dto.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;

    @PostMapping
    public ResponseEntity addCartItem(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @RequestBody CartRequest.AddCartItem request
    ) {
        var command = cartDtoMapper.to(request);
        var response = cartService.addCartItem(loginUser.id(), command).toString();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findCartItems(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = cartService.findCartItems(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public void updateCartItemAmount(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @RequestBody CartRequest.UpdateCartItemAmount request
    ) {
        var command = cartDtoMapper.to(request);
        cartService.updateCartItemAmount(loginUser.id(), command);
    }

    @DeleteMapping
    public void deleteCartItems(
            @AuthenticationPrincipal AuthUser.Login loginUser,
            @RequestBody CartRequest.DeleteCartItems request
    ) {
        var command = cartDtoMapper.to(request);
        cartService.deleteCartItems(loginUser.id(), command);
    }
}
