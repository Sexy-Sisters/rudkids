package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.*;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.Name;
import com.rudkids.rudkids.domain.item.domain.Price;
import com.rudkids.rudkids.domain.item.domain.Quantity;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemMapper itemMapper;
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

    @Override
    public List<ItemInfo.Main> findItems(UUID productId) {
        Product product = productReader.getProduct(productId);
        return product.getItems().stream()
            .map(itemMapper::toMain)
            .toList();
    }

    @Override
    public ItemInfo.Detail findItemDetail(UUID id) {
        Item item = itemReader.getItem(id);
        return itemMapper.toDetail(item);
    }

    @Override
    public String openItem(UUID id) {
        Item item = itemReader.getItem(id);
        item.changeInStock();
        return item.getItemStatus().name();
    }

    @Override
    public String closeItem(UUID id) {
        Item item = itemReader.getItem(id);
        item.changeSoldOut();
        return item.getItemStatus().name();
    }
}
