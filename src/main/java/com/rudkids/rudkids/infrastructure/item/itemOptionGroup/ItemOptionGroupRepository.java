package com.rudkids.rudkids.infrastructure.item.itemOptionGroup;

import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, UUID> {
}
