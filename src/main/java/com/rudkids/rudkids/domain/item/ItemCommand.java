package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class ItemCommand {

    @Builder
    public record RegisterRequest(
        UUID productId,
        String name,
        String itemBio,
        int price,
        int quantity,
        LimitType limitType
    ) {}
}
