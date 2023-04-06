package com.rudkids.rudkids.infrastructure.item.itemOptionGroup;

import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroupStore;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption.ItemOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOptionGroupStoreImpl implements ItemOptionGroupStore {
    private final ItemOptionGroupRepository itemOptionGroupRepository;

    @Override
    public ItemOptionGroup store(ItemOptionGroup itemOptionGroup) {
        return itemOptionGroupRepository.save(itemOptionGroup);
    }

}
