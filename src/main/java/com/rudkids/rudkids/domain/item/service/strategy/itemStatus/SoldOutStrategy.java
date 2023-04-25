package com.rudkids.rudkids.domain.item.service.strategy.itemStatus;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import org.springframework.stereotype.Component;

@Component
public class SoldOutStrategy implements ItemStatusChangeStrategy {

    @Override
    public boolean isItemStatus(ItemStatus itemStatus) {
        return ItemStatus.SOLD_OUT == itemStatus;
    }

    @Override
    public void changeStatus(Item item) {
        item.changeSoldOut();
    }
}
