package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Item findByNameValue(String name);
}
