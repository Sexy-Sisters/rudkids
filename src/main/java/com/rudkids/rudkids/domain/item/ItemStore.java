package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;

import java.util.UUID;

public interface ItemStore {
    Item store(Item item);
    void delete(UUID itemId);
}
