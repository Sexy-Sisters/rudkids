package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
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
}
