package com.rudkids.rudkids.domain.item;

import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.Name;
import com.rudkids.rudkids.domain.item.domain.Price;
import com.rudkids.rudkids.domain.item.domain.Quantity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


    public Item toEntity(ItemCommand.RegisterItemRequest command) {
        var name = Name.create(command.name());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());

        return Item.builder()
            .name(name)
            .price(price)
            .quantity(quantity)
            .limitType(command.limitType())
            .build();
    }
    public ItemInfo.Main toMain(Item item) {
        return ItemInfo.Main.builder()
            .name(item.getName())
            .price(item.getPrice())
            .itemStatus(item.getItemStatus())
            .build();
    }

    public ItemInfo.Detail toDetail(Item item) {
        return ItemInfo.Detail.builder()
            .name(item.getName())
            .bio(item.getBio())
            .price(item.getPrice())
            .quantity(item.getQuantity())
            .limitType(item.getLimitType())
            .itemStatus(item.getItemStatus())
            .build();
    }
}
