package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemFactory;
import com.rudkids.rudkids.domain.item.domain.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public Item create(ItemCommand.CreateItemRequest command) {
        var name = Name.create(command.name());
        var itemBio = ItemBio.create(command.itemBio());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());
        var limitType = command.limitType();

        var initItem = Item.create(name, itemBio, price, quantity, limitType);
        command.images().stream()
            .map(img -> ItemImage.create(initItem, img.path(), img.url()))
            .forEach(initItem::addImage);
        return initItem;
    }

    @Override
    public void update(Item item, ItemCommand.UpdateRequest command) {
        var name = Name.create(command.name());
        var itemBio = ItemBio.create(command.itemBio());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());
        var limitType = command.limitType();
        command.images().stream()
            .map(img -> ItemImage.create(item, img.path(), img.url()))
            .forEach(item::addImage);
        item.update(name, itemBio, price, quantity, limitType);
    }
}