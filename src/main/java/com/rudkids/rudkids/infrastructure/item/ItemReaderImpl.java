package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.domain.item.Item;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {
    private final ItemRepository itemRepository;

    @Override
    public Item getItem(UUID id) {
        return itemRepository.findById(id)
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Item getItem(String name) {
        return itemRepository.findByNameValue(name)
            .orElseThrow(ItemNotFoundException::new);
    }
}
