package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductResponse;
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
    public ResponseEntity findAll() {
        var response = productService.findAll().stream()
            .map(productDtoMapper::toResponse)
            .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id) {
        var info = productService.find(id);
        ProductResponse.Detail response = productDtoMapper.toResponse(info);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void changeStatus(
        @PathVariable(name = "id") UUID productId,
        @RequestBody ProductRequest.ChangeStatus request,
        @AuthenticationPrincipal AuthUser.Login loginUser
    ) {
        productService.changeStatus(request.productStatus(), productId, loginUser.id());
    }
}
