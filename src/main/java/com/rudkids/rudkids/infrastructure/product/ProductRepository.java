package com.rudkids.rudkids.infrastructure.product;

import com.rudkids.rudkids.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByTitleValue(String title);
    List<Product> findByTitleValueContaining(String title);
}
