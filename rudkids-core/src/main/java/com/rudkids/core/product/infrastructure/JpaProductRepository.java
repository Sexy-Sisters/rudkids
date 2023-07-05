package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.productCategory = :category")
    List<Product> findAllByCategory(@Param("category") ProductCategory category);
}