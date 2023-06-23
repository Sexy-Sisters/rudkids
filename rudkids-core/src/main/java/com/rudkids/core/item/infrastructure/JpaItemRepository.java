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
    boolean existsByNameEnNameOrNameKoName(String enName, String koName);

    Page<Item> findByProduct(Product product, Pageable pageable);

    @Query("SELECT i.id FROM Item i WHERE i.name.enName LIKE CONCAT('%', :name, '%')")
    List<UUID> findIdsByName(@Param("name") String name);

    @Query("SELECT i FROM Item i ORDER BY CASE WHEN i.itemStatus = com.rudkids.core.item.domain.ItemStatus.SOLD_OUT THEN 1 ELSE 0 END, i.quantity.value ASC")
    Page<Item> findAllByOrderByStatusAndQuantityAsc(Pageable pageable);
}