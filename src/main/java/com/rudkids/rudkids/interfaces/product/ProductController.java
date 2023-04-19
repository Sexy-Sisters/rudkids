package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public void create(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody ProductRequest.Register request
    ) {
        var command = productDtoMapper.toCommand(request);
        productService.create(command, loginUser.id());
    }

    @GetMapping
    public ResponseEntity findProducts() {
        var response = productService.findAll().stream()
            .map(productDtoMapper::toResponse)
            .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity findProduct(@PathVariable(name = "product-id") UUID productId) {
        var info = productService.find(productId);
        var response = productDtoMapper.toResponse(info);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void openProduct(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        productService.openProduct(id, loginUser.id());
    }

    @DeleteMapping("/{id}")
    public void closeProduct(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        productService.closeProduct(id, loginUser.id());
    }
}
