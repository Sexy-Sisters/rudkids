package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByTitleValueContaining(String title);

    @Query("SELECT p.frontImage.path FROM Product p")
    List<String> findPathsByFrontImage();

    @Query("SELECT p.backImage.path FROM Product p")
    List<String> findPathsByBackImage();

    @Query("SELECT p.productBannerImages FROM Product p")
    List<List<String>> findPathsByBannerImage();
}