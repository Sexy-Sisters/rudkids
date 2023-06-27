package com.rudkids.core.item.domain;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ItemRepository {
    void save(Item item);
    Item get(UUID id);
    Item getByEnNme(String name);
    Page<Item> getPopularItems(Pageable pageable);
    List<Item> getAll();
    void delete(Item item);
    Page<Item> get(Product product, Pageable pageable);
}
