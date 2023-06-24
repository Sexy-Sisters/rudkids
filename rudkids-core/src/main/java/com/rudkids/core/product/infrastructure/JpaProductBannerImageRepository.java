package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.ProductBannerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaProductBannerImageRepository extends JpaRepository<ProductBannerImage, UUID> {

    @Query("SELECT p.path FROM ProductBannerImage p WHERE p.deleted = true")
    List<String> findPathsByDeletedTrue();
}
