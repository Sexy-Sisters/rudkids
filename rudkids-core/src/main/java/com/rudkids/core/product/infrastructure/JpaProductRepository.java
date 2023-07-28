package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p ORDER BY p.createdAt ASC")
    Page<Product> findAllOrderByCreatedAtAsc(Pageable pageable);
    boolean existsByTitleValue(String title);
}