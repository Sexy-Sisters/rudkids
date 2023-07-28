package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.exception.DuplicateProductTitleException;
import com.rudkids.core.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository productRepository;

    @Override
    public void save(Product product) {
        validateDuplicatedTitle(product.getTitle());
        productRepository.save(product);
    }

    private void validateDuplicatedTitle(String title) {
        if(productRepository.existsByTitleValue(title)) {
            throw new DuplicateProductTitleException();
        }
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAllOrderByCreatedAtAsc(pageable);
    }

    @Override
    public Product get(UUID productId) {
        return productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
