package com.rudkids.rudkids.infrastructure.item;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemFactory;
import com.rudkids.rudkids.domain.item.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public Item create(ItemCommand.CreateItemRequest command) {
        var name = Name.create(command.name());
        var itemBio = ItemBio.create(command.itemBio());
        var price = Price.create(command.price());
        var quantity = Quantity.create(command.quantity());
        var limitType = command.limitType();
        return Item.create(name, itemBio, price, quantity, limitType);
    }
}
