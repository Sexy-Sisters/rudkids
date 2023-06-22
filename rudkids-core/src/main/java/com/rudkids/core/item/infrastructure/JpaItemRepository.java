package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findByProduct(Product product, Pageable pageable);

    @Query("SELECT i.id FROM Item i WHERE i.name.enName = :name")
    List<UUID> findIdsByName(@Param("name") String name);

    Page<Item> findAllByOrderByQuantityValueAsc(Pageable pageable);
}