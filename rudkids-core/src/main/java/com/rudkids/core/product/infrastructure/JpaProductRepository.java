package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
}