package com.rudkids.api.product;

import com.rudkids.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity getAll(@PageableDefault Pageable pageable) {
        var response = productService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable UUID id, @PageableDefault Pageable pageable) {
        var response = productService.get(id, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String title) {
        var response = productService.search(title);
        return ResponseEntity.ok(response);
    }
}
