package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import lombok.Builder;

public class ItemResponse {
    @Builder
    public record Main(
        String name,
        int price,
        ItemStatus itemStatus
    ) {
    }
}
