package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCollectionRepository extends JpaRepository<Collection, UUID> {
}
