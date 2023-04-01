package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.ProductBio;
import com.rudkids.rudkids.domain.product.domain.Title;
import com.rudkids.rudkids.util.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;



@ServiceTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemReader itemReader;

    @Autowired
    private ProductStore productStore;

    @Autowired
    private ItemStore itemStore;

    private Product product;
    private Item item;

    @BeforeEach
    void inputData() {
        product = Product.builder()
            .title(Title.create("Strange Drugstore"))
            .bio(ProductBio.create("약국입니다~"))
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
    }

    @DisplayName("상품 등록 성공")
    @Test
    void registerItem() {
        ItemCommand.RegisterRequest command = ItemCommand.RegisterRequest.builder()
            .productId(product.getId())
            .name("Red Pill")
            .price(1_000_000)
            .quantity(1)
            .limitType(LimitType.LIMITED)
            .build();
        itemService.registerItem(command);

        Item findItem = itemReader.getItem(command.getName());
        assertThat(findItem.getName()).isEqualTo("Red Pill");
    }

    @DisplayName("특정 프로덕트의 아이템 리스트 조회")
    @Test
    void findItems() {
        List<ItemCommand.RegisterRequest> commandList = List.of(
            ItemCommand.RegisterRequest.builder()
                .productId(product.getId())
                .name("No.2")
                .price(2_990)
                .quantity(1_000)
                .limitType(LimitType.NORMAL)
                .build(),
            ItemCommand.RegisterRequest.builder()
                .productId(product.getId())
                .name("No.3")
                .price(2_990)
                .quantity(1_000)
                .limitType(LimitType.NORMAL)
                .build()
        );
        commandList.forEach(itemService::registerItem);

        List<ItemInfo.Main> items = itemService.findItems(product.getId());
        assertThat(items).hasSize(2);
    }

    @DisplayName("아이템 상세 조회")
    @Test
    void findItemDetail() {
        ItemInfo.Detail findItem = itemService.findItemDetail(item.getId());

        assertAll(
            () -> assertThat(findItem.getName()).isEqualTo("No.1"),
            () -> assertThat(findItem.getBio()).isEqualTo("소개글입니다~"),
            () -> assertThat(findItem.getPrice()).isEqualTo(2_990),
            () -> assertThat(findItem.getQuantity()).isEqualTo(1_000),
            () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED)
        );
    }

    @DisplayName("아이템 판매 종료")
    @Test
    void openItem() {
        String itemStatus = itemService.openItem(item.getId());
        assertThat(itemStatus).isEqualTo("IN_STOCK");
    }

    @DisplayName("아이템 판매 종료")
    @Test
    void closeItem() {
        String itemStatus = itemService.closeItem(item.getId());
        assertThat(itemStatus).isEqualTo("SOLD_OUT");
    }
}
