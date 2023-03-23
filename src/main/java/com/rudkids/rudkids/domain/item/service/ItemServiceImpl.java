package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.Name;
import com.rudkids.rudkids.domain.item.domain.Price;
import com.rudkids.rudkids.domain.item.domain.Quantity;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;
    private final ProductReader productReader;

    @Override
    @Transactional
    public void registerItem(ItemCommand.RegisterRequest command) {
        Name name = Name.create(command.getName());
        Price price = Price.create(command.getPrice());
        Quantity quantity = Quantity.create(command.getQuantity());

        Item initItem = Item.builder()
            .name(name)
            .price(price)
            .quantity(quantity)
            .limitType(command.getLimitType())
            .build();

        Product product = productReader.getProduct(command.getProductId());
        initItem.changeProduct(product);

        itemStore.store(initItem);
    }
}