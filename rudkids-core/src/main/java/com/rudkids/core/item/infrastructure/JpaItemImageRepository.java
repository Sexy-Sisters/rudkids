package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaItemImageRepository extends JpaRepository<ItemImage, UUID> {
}
