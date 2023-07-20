package com.rudkids.core.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductRepository {
    void save(Product product);
    Page<Product> getAll(Pageable pageable);
    Product get(UUID productId);
    void delete(Product product);
}