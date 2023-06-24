package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaItemImageRepository extends JpaRepository<ItemImage, UUID> {

    @Query("SELECT i.path FROM ItemImage i WHERE i.deleted is true")
    List<String> findPathsByDeletedTrue();
}
