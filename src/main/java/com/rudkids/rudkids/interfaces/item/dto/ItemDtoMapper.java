package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDtoMapper {

    public ItemCommand.RegisterItemRequest to(ItemRequest.RegisterItem request) {
        var itemOptionGroupRequestList = request.itemOptionGroupList().stream()
            .map(this::to)
            .toList();

        return ItemCommand.RegisterItemRequest.builder()
            .productId(request.productId())
            .name(request.name())
            .price(request.price())
            .quantity(request.quantity())
            .limitType(request.limitType())
            .itemOptionGroupList(itemOptionGroupRequestList)
            .build();
    }

    public ItemCommand.RegisterItemOptionGroupRequest to(ItemRequest.RegisterItemOptionGroup request) {
        var itemOptionList = request.itemOptionList().stream()
            .map(this::to)
            .toList();


        return ItemCommand.RegisterItemOptionGroupRequest.builder()
            .ordering(request.ordering())
            .itemOptionGroupName(request.itemOptionGroupName())
            .itemOptionList(itemOptionList)
            .build();
    }

    public ItemCommand.RegisterItemOptionRequest to(ItemRequest.RegisterItemOption request) {
        return ItemCommand.RegisterItemOptionRequest.builder()
            .ordering(request.ordering())
            .itemOptionName(request.itemOptionName())
            .itemOptionPrice(request.itemOptionPrice())
            .build();
    }

    public ItemResponse.Main to(ItemInfo.Main info) {
        return ItemResponse.Main.builder()
            .name(info.name())
            .price(info.price())
            .itemStatus(info.itemStatus())
            .build();
    }

    public ItemResponse.Detail to(ItemInfo.Detail info) {
        return ItemResponse.Detail.builder()
            .name(info.name())
            .price(info.price())
            .bio(info.bio())
            .quantity(info.quantity())
            .limitType(info.limitType())
            .itemStatus(info.itemStatus())
            .build();
    }
}
