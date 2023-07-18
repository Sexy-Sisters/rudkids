package com.rudkids.core.admin.infrastructure;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.*;
import com.rudkids.core.item.domain.itemOption.ItemOption;
import com.rudkids.core.item.domain.itemOption.ItemOptionName;
import com.rudkids.core.item.domain.itemOption.ItemOptionPrice;
import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroup;
import com.rudkids.core.item.domain.itemOptionGroup.ItemOptionGroupName;
import com.rudkids.core.admin.service.ItemFactory;
import com.rudkids.core.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public Item create(Product product, AdminRequest.CreateItem request) {
        var item = generateItem(product, request);
        generateChildEntities(item, request);
        return item;
    }

    private Item generateItem(Product product, AdminRequest.CreateItem request) {
        var name = Name.create(request.enName(), request.koName());
        var itemBio = ItemBio.create(request.itemBio());
        var price = Price.create(request.price());
        var quantity = Quantity.create(request.quantity());
        var limitType = request.limitType();

        return Item.builder()
            .product(product)
            .name(name)
            .itemBio(itemBio)
            .price(price)
            .quantity(quantity)
            .limitType(limitType)
            .build();
    }

    private void generateChildEntities(Item item, AdminRequest.CreateItem request) {
        for (AdminRequest.CreateImage imageRequest : request.images()) {
            var image = ItemImage.create(
                item,
                imageRequest.path(),
                imageRequest.url(),
                imageRequest.ordering()
            );
            item.addImage(image);
        }

        request.itemOptionGroupList().forEach(group -> {
            var optionGroup = generateItemOptionGroup(item, group);

            group.itemOptionList().forEach(option -> {
                var itemOption = generateItemOption(optionGroup, option);
                optionGroup.addItemOption(itemOption);
            });

            item.addOptionGroup(optionGroup);
        });
    }

    private ItemOptionGroup generateItemOptionGroup(Item item, AdminRequest.CreateItemOptionGroup group) {
        var groupName = ItemOptionGroupName.create(group.itemOptionGroupName());

        return ItemOptionGroup.builder()
            .item(item)
            .itemOptionGroupName(groupName)
            .ordering(group.ordering())
            .build();
    }

    private ItemOption generateItemOption(ItemOptionGroup optionGroup, AdminRequest.CreateItemOption option) {
        var itemOptionName = ItemOptionName.create(option.itemOptionName());
        var itemOptionPrice = ItemOptionPrice.create(option.itemOptionPrice());

        return ItemOption.builder()
            .itemOptionGroup(optionGroup)
            .itemOptionName(itemOptionName)
            .itemOptionPrice(itemOptionPrice)
            .ordering(option.ordering())
            .build();
    }

    @Override
    public void update(Item item, AdminRequest.UpdateItem request) {
        var name = Name.create(request.enName(), request.koName());
        var itemBio = ItemBio.create(request.itemBio());
        var price = Price.create(request.price());
        var quantity = Quantity.create(request.quantity());
        var limitType = request.limitType();

        for (ImageRequest.Create imageRequest : request.images()) {

            for(ItemImage image: item.getImages()) {
                image.update(imageRequest.path(), imageRequest.url());
            }
        }

        item.update(name, itemBio, price, quantity, limitType);
    }
}
