package com.rudkids.core.item.domain;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ItemRepository {
    void save(Item item);
    Item get(UUID id);
    void delete(Item item);
    Page<Item> get(Product product, Pageable pageable);
}
