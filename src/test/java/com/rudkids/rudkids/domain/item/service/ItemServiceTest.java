package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Bio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.Title;
import com.rudkids.rudkids.util.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @BeforeEach
    void inputData() {
        product = Product.builder()
            .title(Title.create("약국"))
            .bio(Bio.create("약국입니다~"))
            .build();
        productStore.store(product);
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
                .name("No.1")
                .price(2_990)
                .quantity(1_000)
                .limitType(LimitType.NORMAL)
                .build(),
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
                .build(),
            ItemCommand.RegisterRequest.builder()
                .productId(product.getId())
                .name("No.4")
                .price(2_990)
                .quantity(1_000)
                .limitType(LimitType.NORMAL)
                .build()
        );
        commandList.forEach(itemService::registerItem);

        List<ItemInfo.Main> items = itemService.findItems(product.getId());

        assertThat(items.size()).isEqualTo(4);
    }
}
