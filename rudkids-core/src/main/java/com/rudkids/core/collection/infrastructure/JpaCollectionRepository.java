package com.rudkids.core.collection.infrastructure;

import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaCollectionRepository extends JpaRepository<Collection, UUID> {
    Optional<Collection> findByUser(User user);
}
