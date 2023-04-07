package com.rudkids.rudkids.infrastructure.item.itemOptionGroup.itemOption;

import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOptionStoreImpl implements ItemOptionStore {
    private final ItemOptionRepository itemOptionRepository;

    @Override
    public ItemOption store(ItemOption itemOption) {
        return itemOptionRepository.save(itemOption);
    }
}
