package com.rudkids.api.product;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.product.service.MysteryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/mystery")
public class MysteryProductController {
    private final MysteryProductService mysteryProductService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") UUID mysteryProductId) {
        var response = mysteryProductService.get(mysteryProductId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity getDetail(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @PathVariable("id")UUID mysteryProductId
    ) {
        var response = mysteryProductService.getDetail(loginUser.id(), mysteryProductId);
        return ResponseEntity.ok(response);
    }
}
