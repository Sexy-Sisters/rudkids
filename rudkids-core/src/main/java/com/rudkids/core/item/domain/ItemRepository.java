package com.rudkids.core.item.domain;

import com.rudkids.core.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepository {
    void save(Item item);
    Item getByEnNme(String name);
    Page<Item> getPopularItems(Pageable pageable);
    Page<Item> getAll(Pageable pageable);
    void delete(Item item);
    Page<Item> get(Product product, Pageable pageable);
}
