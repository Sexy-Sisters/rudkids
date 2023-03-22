package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {
    private final ProductRepository productRepository;

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product getProduct(String title) {
        return productRepository.findByTitleValue(title)
            .orElseThrow(ProductNotFoundException::new);
    }
}
