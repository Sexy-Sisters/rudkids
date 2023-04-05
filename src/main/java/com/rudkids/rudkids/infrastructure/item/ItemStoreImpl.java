package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {
    private final ItemRepository itemRepository;

    @Override
    public void store(Item item) {
        itemRepository.save(item);
    }
}
