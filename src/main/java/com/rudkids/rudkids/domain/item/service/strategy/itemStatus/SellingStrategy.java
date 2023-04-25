package com.rudkids.rudkids.domain.item.service.strategy.itemStatus;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import org.springframework.stereotype.Component;

@Component
public class SellingStrategy implements ItemStatusChangeStrategy {

    @Override
    public boolean isItemStatus(ItemStatus itemStatus) {
        return ItemStatus.SELLING == itemStatus;
    }

    @Override
    public void changeStatus(Item item) {
        item.changeSelling();
    }
}
