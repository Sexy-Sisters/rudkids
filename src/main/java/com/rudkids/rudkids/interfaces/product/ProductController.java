package com.rudkids.rudkids.interfaces.product;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public void registerProduct(@RequestBody ProductRequest.Register request) {
        ProductCommand.RegisterRequest command = productDtoMapper.to(request);
        productService.registerProduct(command);
    }

    @GetMapping
    public List<ProductResponse.Main> findProducts() {
        return productService.findProducts().stream()
            .map(productDtoMapper::to)
            .toList();
    }

    @PutMapping("/{id}")
    public void openProduct(@PathVariable UUID id) {
        productService.openProduct(id);
    }

    @DeleteMapping("/{id}")
    public void closeProduct(@PathVariable UUID id) {
        productService.closeProduct(id);
    }
}
