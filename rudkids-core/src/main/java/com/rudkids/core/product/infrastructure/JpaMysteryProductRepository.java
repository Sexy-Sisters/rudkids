package com.rudkids.core.product.infrastructure;

import com.rudkids.core.product.domain.MysteryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMysteryProductRepository extends JpaRepository<MysteryProduct, UUID> {
}
