package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

import java.util.UUID;

public class ItemRequest {

    @Builder
    public record Register(
        UUID productId,
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType
    ) {
    }
}
