package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

import java.util.UUID;

public class ItemResponse {
    @Builder
    public record Main(
        UUID id,
        String name,
        int price,
        ItemStatus itemStatus
    ) {
    }

    @Builder
    public record Detail(
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType,
        ItemStatus itemStatus
    ) {
    }
}
