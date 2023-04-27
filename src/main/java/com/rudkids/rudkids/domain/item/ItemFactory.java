package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;

public interface ItemFactory {
    Item create(ItemCommand.CreateItemRequest command);
}
