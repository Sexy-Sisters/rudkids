package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class ItemRequest {

    @Builder
    public record RegisterItem(
        UUID productId,
        String name,
        int price,
        int quantity,
        LimitType limitType,
        List<ItemRequest.RegisterItemOptionGroup> itemOptionGroupList
    ) {}

    @Builder
    public record RegisterItemOptionGroup(
        Integer ordering,
        String itemOptionGroupName,
        List<ItemRequest.RegisterItemOption> itemOptionList
    ) {}

    @Builder
    public record RegisterItemOption(
        Integer ordering,
        String itemOptionName,
        int itemOptionPrice
    ) {}
}
