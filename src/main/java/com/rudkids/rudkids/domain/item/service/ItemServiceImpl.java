package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.Name;
import com.rudkids.rudkids.domain.item.domain.Price;
import com.rudkids.rudkids.domain.item.domain.Quantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;

    @Override
    @Transactional
    public void registerItem(ItemCommand.CreateRequest command) {
        Name name = Name.create(command.getName());
        Price price = Price.create(command.getPrice());
        Quantity quantity = Quantity.create(command.getQuantity());

        Item initItem = Item.builder()
            .name(name)
            .price(price)
            .quantity(quantity)
            .limitType(command.getLimitType())
            .build();

        itemStore.store(initItem);
    }
}
