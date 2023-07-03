package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemImage;
import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaItemRepository extends JpaRepository<Item, UUID> {
    boolean existsByNameEnNameOrNameKoName(String enName, String koName);

    Optional<Item> findByNameEnName(String enName);

    Page<Item> findByProduct(Product product, Pageable pageable);

    @Query("SELECT i FROM Item i ORDER BY CASE WHEN i.itemStatus = com.rudkids.core.item.domain.ItemStatus.SOLD_OUT THEN 1 ELSE 0 END, i.quantity.value ASC")
    Page<Item> findAllByOrderByStatusAndQuantityAsc(Pageable pageable);
}