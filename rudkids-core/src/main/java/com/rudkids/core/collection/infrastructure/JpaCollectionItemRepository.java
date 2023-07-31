package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCollectionItemRepository extends JpaRepository<CollectionItem, UUID> {
    Optional<CollectionItem> findByItem(Item item);
}
