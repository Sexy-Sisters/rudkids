package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByNameValue(String name);
}
