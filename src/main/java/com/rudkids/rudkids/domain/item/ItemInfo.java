package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.item.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.item.LimitType;
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
        String bio,
        int price,
        int quantity,
        LimitType limitType,
        ItemStatus itemStatus
    ) {}
}
