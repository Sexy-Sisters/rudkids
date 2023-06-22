package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findByProduct(Product product, Pageable pageable);
}
