package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {


    public Item toEntity(ItemCommand.CreateItemRequest command) {
        var name = Name.create(command.name());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());
        var itemBio = ItemBio.create(command.itemBio());

        return Item.builder()
            .name(name)
            .price(price)
            .quantity(quantity)
            .itemBio(itemBio)
            .limitType(command.limitType())
            .build();
    }

    public ItemInfo.Main toMain(Item item) {
        return ItemInfo.Main.builder()
            .id(item.getId())
            .name(item.getName())
            .price(item.getPrice())
            .itemStatus(item.getItemStatus())
            .build();
    }

    public ItemInfo.Detail toDetail(Item item, List<ItemInfo.ItemOptionGroupInfo> itemOptionSeriesList) {
        return ItemInfo.Detail.builder()
            .name(item.getName())
            .itemBio(item.getItemBio())
            .price(item.getPrice())
            .quantity(item.getQuantity())
            .limitType(item.getLimitType())
            .imageUrls(item.getImageUrls())
            .itemStatus(item.getItemStatus())
            .itemOptionGroupInfoList(itemOptionSeriesList)
            .build();
    }
}
