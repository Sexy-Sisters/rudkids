package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.exception.DuplicateProductTitleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {
    private final ProductRepository productRepository;

    @Override
    public void store(Product product) {
        checkDuplicateTitle(product.getTitle());
        productRepository.save(product);
    }

    private void checkDuplicateTitle(String title) {
        if(productRepository.existsByTitleValue(title)) {
            throw new DuplicateProductTitleException();
        }
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
