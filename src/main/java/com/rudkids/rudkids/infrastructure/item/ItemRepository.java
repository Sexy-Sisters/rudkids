package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findByProduct(Product product, Pageable pageable);
}
