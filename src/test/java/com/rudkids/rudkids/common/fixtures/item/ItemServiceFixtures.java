package com.rudkids.rudkids.common.fixtures.item;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Title;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class ItemServiceFixtures {
    @Autowired
    protected ItemService itemService;

    @Autowired
    protected ItemReader itemReader;

    @Autowired
    protected ProductStore productStore;

    @Autowired
    protected ItemStore itemStore;

    protected static Product product;
    protected static Item item;
    protected static ItemCommand.RegisterItemRequest ITEM_등록_요청;

    private static final ItemCommand.RegisterItemOptionGroupRequest itemOptionGroup_사이즈 = ItemCommand.RegisterItemOptionGroupRequest.builder()
        .ordering(1)
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            ItemCommand.RegisterItemOptionRequest.builder()
                .ordering(1)
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            ItemCommand.RegisterItemOptionRequest.builder()
                .ordering(2)
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            ItemCommand.RegisterItemOptionRequest.builder()
                .ordering(3)
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

    @BeforeEach
    void inputData() {
        product = Product.builder()
            .title(Title.create("Strange Drugstore"))
            .productBio(ProductBio.create("약국입니다~"))
            .build();
        productStore.store(product);

        item = Item.builder()
            .name(Name.create("No.1"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.LIMITED)
            .build();
        itemStore.store(item);

        ITEM_등록_요청 = ItemCommand.RegisterItemRequest.builder()
            .name("Red Pill")
            .price(1_000_000)
            .quantity(1)
            .itemBio("소개글입니다~")
            .limitType(LimitType.LIMITED)
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .build();
    }
}
