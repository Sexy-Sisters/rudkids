package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.item.LimitType;
import lombok.Builder;

import java.util.UUID;

public class ItemRequest {

    @Builder
    public record Register(
        UUID productId,
        String name,
        int price,
        int quantity,
        LimitType limitType
    ) {
    }
}
