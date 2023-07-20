package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.ProductBannerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductBannerImageRepository extends JpaRepository<ProductBannerImage, UUID> {
}
