package com.rudkids.rudkids.interfaces.item.dto;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public ItemCommand.CreateItemRequest to(ItemRequest.RegisterItem request) {
        var itemOptionGroupRequestList = request.itemOptionGroupList().stream()
            .map(this::to)
            .toList();

        return ItemCommand.CreateItemRequest.builder()
            .name(request.name())
            .itemBio(request.itemBio())
            .price(request.price())
            .quantity(request.quantity())
            .itemBio(request.itemBio())
            .limitType(request.limitType())
            .images(request.images())
            .itemOptionGroupList(itemOptionGroupRequestList)
            .build();
    }

    public ItemCommand.CreateItemOptionGroupRequest to(ItemRequest.RegisterItemOptionGroup request) {
        var itemOptionList = request.itemOptionList().stream()
            .map(this::to)
            .toList();


        return ItemCommand.CreateItemOptionGroupRequest.builder()
            .ordering(request.ordering())
            .itemOptionGroupName(request.itemOptionGroupName())
            .itemOptionList(itemOptionList)
            .build();
    }

    public ItemCommand.CreateItemOptionRequest to(ItemRequest.RegisterItemOption request) {
        return ItemCommand.CreateItemOptionRequest.builder()
            .ordering(request.ordering())
            .itemOptionName(request.itemOptionName())
            .itemOptionPrice(request.itemOptionPrice())
            .build();
    }

    public ItemResponse.Main to(ItemInfo.Main info) {
        return ItemResponse.Main.builder()
            .id(info.id())
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
            .itemBio(info.itemBio())
            .quantity(info.quantity())
            .limitType(info.limitType())
            .imageUrls(info.imageUrls())
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

    public ItemCommand.UpdateRequest toCommand(ItemRequest.Update request) {
        return ItemCommand.UpdateRequest.builder()
            .name(request.name())
            .itemBio(request.itemBio())
            .name(request.name())
            .quantity(request.quantity())
            .limitType(request.limitType())
            .images(request.images())
            .build();
    }
}
