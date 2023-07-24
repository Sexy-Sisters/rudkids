package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCollectionItemRepository extends JpaRepository<CollectionItem, UUID> {
}
