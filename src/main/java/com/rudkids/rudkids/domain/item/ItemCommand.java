package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.item.LimitType;
import lombok.Builder;

import java.util.UUID;

public class ItemCommand {

    @Builder
    public record RegisterRequest(
        UUID productId,
        String name,
        int price,
        int quantity,
        LimitType limitType
    ) {}
}
