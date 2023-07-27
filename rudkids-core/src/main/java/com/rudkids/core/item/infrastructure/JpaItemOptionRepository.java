package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaItemOptionRepository extends JpaRepository<ItemOption, UUID> {
}
