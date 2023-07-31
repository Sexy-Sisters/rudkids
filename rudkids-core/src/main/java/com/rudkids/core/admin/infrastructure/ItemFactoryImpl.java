package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.domain.option.ItemOption;
import com.rudkids.core.item.domain.option.ItemOptionName;
import com.rudkids.core.item.domain.option.ItemOptionPrice;
import com.rudkids.core.item.domain.optionGroup.ItemOptionGroup;
import com.rudkids.core.item.domain.optionGroup.ItemOptionGroupName;
import com.rudkids.core.admin.service.ItemFactory;
import com.rudkids.core.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public Item create(Product product, AdminRequest.CreateItem request) {
        var item = generateItem(product, request);
        saveItemImages(item, request.images());
        saveChildEntities(item, request.itemOptionGroupInfoList());
        return item;
    }

    private Item generateItem(Product product, AdminRequest.CreateItem request) {
        var name = Name.create(request.enName(), request.koName());
        var itemBio = ItemBio.create(request.itemBio());
        var price = Price.create(request.price());
        var quantity = Quantity.create(request.quantity());
        var limitType = request.limitType();
        var grayImage = GrayImage.create(request.grayImage().path(), request.grayImage().url());

        return Item.builder()
            .product(product)
            .name(name)
            .itemBio(itemBio)
            .price(price)
            .quantity(quantity)
            .limitType(limitType)
            .grayImage(grayImage)
            .videoUrl(request.videoUrl())
            .mysteryItemName(request.mysteryItemName())
            .build();
    }

    @Override
    public void update(Item item, AdminRequest.UpdateItem request) {
        var name = Name.create(request.enName(), request.koName());
        var itemBio = ItemBio.create(request.itemBio());
        var price = Price.create(request.price());
        var quantity = Quantity.create(request.quantity());
        var limitType = request.limitType();
        var grayImage = GrayImage.create(request.grayImage().path(), request.grayImage().url());

        item.update(name, itemBio, price, quantity, limitType, grayImage, request.videoUrl());
        saveItemImages(item, request.images());
        saveChildEntities(item, request.itemOptionGroupInfoList());
    }

    private void saveItemImages(Item item, List<AdminRequest.Image> images) {
        for(AdminRequest.Image image: images) {
            var itemImage = ItemImage.create(
                item,
                image.path(),
                image.url(),
                image.ordering()
            );
            item.addImage(itemImage);
        }
    }

    private void saveChildEntities(Item item, List<AdminRequest.ItemOptionGroup> itemOptionGroups) {
        itemOptionGroups.forEach(group -> {
            var optionGroup = generateItemOptionGroup(item, group);

            group.itemOptionInfoList().forEach(option -> {
                var itemOption = generateItemOption(optionGroup, option);
                optionGroup.addItemOption(itemOption);
            });

            item.addOptionGroup(optionGroup);
        });
    }

    private ItemOptionGroup generateItemOptionGroup(Item item, AdminRequest.ItemOptionGroup group) {
        var groupName = ItemOptionGroupName.create(group.itemOptionGroupName());

        return ItemOptionGroup.builder()
            .item(item)
            .itemOptionGroupName(groupName)
            .ordering(group.ordering())
            .build();
    }

    private ItemOption generateItemOption(ItemOptionGroup optionGroup, AdminRequest.ItemOption option) {
        var itemOptionName = ItemOptionName.create(option.itemOptionName());
        var itemOptionPrice = ItemOptionPrice.create(option.itemOptionPrice());

        return ItemOption.builder()
            .itemOptionGroup(optionGroup)
            .itemOptionName(itemOptionName)
            .itemOptionPrice(itemOptionPrice)
            .ordering(option.ordering())
            .build();
    }
}
