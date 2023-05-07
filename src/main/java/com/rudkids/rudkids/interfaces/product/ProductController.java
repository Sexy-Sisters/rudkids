package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity findAll(@PageableDefault Pageable pageable) {
        var info = productService.findAll(pageable);
        var response = productDtoMapper.toResponse(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable UUID id, @PageableDefault Pageable pageable) {
        var info = productService.find(id, pageable);
        var response = productDtoMapper.toResponse(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String title) {
        var response = productService.search(title);
        return ResponseEntity.ok(response);
    }
}
