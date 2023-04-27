package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.item.domain.itemOption.ItemOption;
import com.rudkids.rudkids.domain.item.domain.itemOptionGroup.ItemOptionGroup;
import lombok.Builder;

import java.util.List;
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
        List<String> imageUrls,
        ItemStatus itemStatus,
        List<ItemOptionGroupResponse> itemOptionGroupResponseList
    ) {
    }

    public record ItemOptionGroupResponse(
        Integer ordering,
        String itemOptionGroupName,
        List<ItemResponse.ItemOptionResponse> itemOptionInfoList
    ) {
    }

    public record ItemOptionResponse(
        Integer ordering,
        String itemOptionName,
        Integer itemOptionPrice
    ) {
    }
}
