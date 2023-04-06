package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class ItemCommand {

    @Builder
    public record RegisterItemRequest(
        UUID productId,
        String name,
        int price,
        int quantity,
        LimitType limitType,
        List<RegisterItemOptionGroupRequest> itemOptionGroupList
    ) {}

    @Builder
    public record RegisterItemOptionGroupRequest(
        Integer ordering,
        String itemOptionGroupName,
        List<RegisterItemOptionRequest> itemOptionList
    ) {}

    @Builder
    public record RegisterItemOptionRequest(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {}
}
