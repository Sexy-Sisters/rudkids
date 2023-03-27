package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.product.ProductInfo;
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

@ServiceTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemReader itemReader;

    @Autowired
    private ProductStore productStore;

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
        Assertions.assertAll(
            () -> assertThat(findItem.getName()).isEqualTo("Red Pill"),
            () -> assertThat(findItem.getPrice()).isEqualTo(1_000_000),
            () -> assertThat(findItem.getQuantity()).isEqualTo(1),
            () -> assertThat(findItem.getLimitType()).isEqualTo(LimitType.LIMITED),
            () -> assertThat(findItem.getItemStatus()).isEqualTo(ItemStatus.IN_STOCK)
        );
    }
}
