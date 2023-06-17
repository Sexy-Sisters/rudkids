package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import lombok.Builder;

import java.util.List;

public class ItemResponse {

    @Builder
    public record Detail(
        String enName,
        String koName,
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
