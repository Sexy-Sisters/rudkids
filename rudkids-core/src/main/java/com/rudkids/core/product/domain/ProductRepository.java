package com.rudkids.core.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    void save(Product product);
    Page<Product> getAll(Pageable pageable);
    Product get(UUID productId);
    List<Product> get(String category);
    void delete(Product product);
}
