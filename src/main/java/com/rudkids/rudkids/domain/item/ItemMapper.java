package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.item.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemInfo.Main toMain(Item item) {
        return ItemInfo.Main.builder()
            .name(item.getName())
            .price(item.getPrice())
            .itemStatus(item.getItemStatus())
            .build();
    }

    public ItemInfo.Detail toDetail(Item item) {
        return ItemInfo.Detail.builder()
            .name(item.getName())
            .bio(item.getBio())
            .price(item.getPrice())
            .quantity(item.getQuantity())
            .limitType(item.getLimitType())
            .itemStatus(item.getItemStatus())
            .build();
    }
}
