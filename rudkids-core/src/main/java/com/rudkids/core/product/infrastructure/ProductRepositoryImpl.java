package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductCategory;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product get(UUID productId) {
        return productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> get(String category) {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
