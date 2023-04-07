package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

public class ItemInfo {

    @Builder
    public record Main (
        String name,
        int price,
        ItemStatus itemStatus
    ) {}

    @Builder
    public record Detail (
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        ItemStatus itemStatus
    ) {}
}
