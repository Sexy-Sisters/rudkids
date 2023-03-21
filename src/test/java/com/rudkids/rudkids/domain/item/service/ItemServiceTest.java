package com.rudkids.rudkids.domain.item.service;

import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.ItemReader;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.Item;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.util.ServiceTest;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ServiceTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemReader itemReader;

//    @BeforeEach
//    void inputData() {
//        Item item = Item.builder()
//            .name(Name.create("레드필"))
//            .price(Price.create(100000))
//            .quantity(Quantity.create(1))
//            .limitType(LimitType.LIMITED)
//            .build();
//        itemStore.store(item);
//    }

    @DisplayName("상품 등록 성공")
    @Rollback(false)
    @Test
    void registerItem() {
        ItemCommand.CreateRequest command = ItemCommand.CreateRequest.builder()
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
