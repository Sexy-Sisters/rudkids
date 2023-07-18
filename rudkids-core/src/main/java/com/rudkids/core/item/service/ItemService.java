package com.rudkids.core.item.service;

import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemResponse.Detail get(String name) {
        var item = itemRepository.getByEnNme(name);
        return new ItemResponse.Detail(item);
    }

    public Page<ItemResponse.Main> getPopularItems(Pageable pageable) {
        return itemRepository.getPopularItems(pageable)
            .map(ItemResponse.Main::new);
    }
}