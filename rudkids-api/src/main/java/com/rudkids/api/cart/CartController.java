package com.rudkids.api.cart;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity addCartItem(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CartRequest.AddCartItem request
    ) {
        var response = cartService.addCartItem(loginUser.id(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getCartItems(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = cartService.getCartItems(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public void updateCartItemAmount(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CartRequest.UpdateCartItemAmount request
    ) {
        cartService.updateCartItemAmount(loginUser.id(), request);
    }

    @DeleteMapping
    public void deleteCartItems(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CartRequest.DeleteCartItems request
    ) {
        cartService.deleteCartItems(loginUser.id(), request);
    }
}
