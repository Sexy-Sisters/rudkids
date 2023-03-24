package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.Bio;
import com.rudkids.rudkids.domain.product.domain.Product;
import com.rudkids.rudkids.domain.product.domain.Title;
import com.rudkids.rudkids.common.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ServiceTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemReader itemReader;

    @Autowired
    private ProductStore productStore;

    Product initProduct = Product.builder()
        .title(Title.create("약국"))
        .bio(Bio.create("약국입니다~"))
        .build();

    @BeforeEach
    void inputData() {
        productStore.store(initProduct);
    }

    @DisplayName("상품 등록 성공")
    @Test
    void registerItem() {
        ItemCommand.RegisterRequest command = ItemCommand.RegisterRequest.builder()
            .productId(initProduct.getId())
            .name("Red Pill")
            .price(1_000_000)
            .quantity(1)
            .limitType(LimitType.LIMITED)
            .build();
        itemService.registerItem(command);

        Item findItem = itemReader.getItem(command.getName());
        assertThat(findItem.getName()).isEqualTo("Red Pill");
    }
}
