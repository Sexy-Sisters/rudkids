package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemDtoMapper {

    public ItemCommand.RegisterItemRequest to(ItemRequest.RegisterItem request) {
        var itemOptionGroupRequestList = request.itemOptionGroupList().stream()
            .map(this::to)
            .toList();

        return ItemCommand.RegisterItemRequest.builder()
            .name(request.name())
            .price(request.price())
            .quantity(request.quantity())
            .itemBio(request.itemBio())
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
        var itemOptionGroupResponseList = info.itemOptionGroupInfoList().stream()
            .map(this::to)
            .toList();

        return ItemResponse.Detail.builder()
            .name(info.name())
            .price(info.price())
            .bio(info.itemBio())
            .quantity(info.quantity())
            .limitType(info.limitType())
            .itemStatus(info.itemStatus())
            .itemOptionGroupResponseList(itemOptionGroupResponseList)
            .build();
    }

    private ItemResponse.ItemOptionGroupResponse to(ItemInfo.ItemOptionGroupInfo info) {
        var itemOptionResponse = info.itemOptionInfoList().stream()
            .map(this::to)
            .toList();
        return new ItemResponse.ItemOptionGroupResponse(
            info.ordering(),
            info.itemOptionGroupName(),
            itemOptionResponse
        );
    }

    private ItemResponse.ItemOptionResponse to(ItemInfo.ItemOptionInfo info) {
        return new ItemResponse.ItemOptionResponse(
            info.ordering(),
            info.itemOptionName(),
            info.itemOptionPrice()
        );
    }
}
