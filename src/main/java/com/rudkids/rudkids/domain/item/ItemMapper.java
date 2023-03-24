package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemInfo.Main of(Item item) {
        return ItemInfo.Main.builder()
            .name(item.getName())
            .price(item.getPrice())
            .itemStatus(item.getItemStatus())
            .build();
    }
}
