package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {
    private final ProductRepository productRepository;

    public void store(Product product) {
        productRepository.save(product);
    }
}
