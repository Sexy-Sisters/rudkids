package com.rudkids.core.item.infrastructure;

import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemOptionGroupRepositoryImpl implements ItemOptionGroupRepository {
    private final JpaItemOptionGroupRepository itemOptionGroupRepository;

    @Override
    public void deletes(List<ItemOptionGroup> groups) {
        itemOptionGroupRepository.deleteAllInBatch(groups);
    }
}
