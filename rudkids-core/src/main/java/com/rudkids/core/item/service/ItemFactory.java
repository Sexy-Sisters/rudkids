package com.rudkids.core.item.service;

import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.dto.ItemRequest;

public interface ItemFactory {
    Item create(ItemRequest.Create request);
    void update(Item item, ItemRequest.Update request);
}