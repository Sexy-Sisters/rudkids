package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByTitleValueContaining(String title);
}
