package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.optionGroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, UUID> {
}
