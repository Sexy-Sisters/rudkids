package com.rudkids.rudkids.domain.item.service.strategy.itemStatus;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;

public interface ItemStatusChangeStrategy {
    boolean isItemStatus(ItemStatus itemStatus);
    void changeStatus(Item item);
}
