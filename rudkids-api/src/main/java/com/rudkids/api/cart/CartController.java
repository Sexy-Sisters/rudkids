package com.rudkids.api.cart;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/select")
    public ResponseEntity getSelectedCartItems(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = cartService.getSelected(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<Void> updateCartItemAmount(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CartRequest.UpdateCartItemAmount request
    ) {
        cartService.updateCartItemAmount(loginUser.id(), request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/select")
    public ResponseEntity<Void> selectCartItem(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody CartRequest.SelectCartItem request
    ) {
        cartService.select(loginUser.id(), request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id") UUID cartItemId
    ) {
        cartService.deleteCartItem(loginUser.id(), cartItemId);
        return ResponseEntity.ok().build();
    }
}
