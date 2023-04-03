package com.rudkids.rudkids.common.fixtures.item;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.rudkids.rudkids.domain.item.ItemCommand;
import com.rudkids.rudkids.domain.item.domain.LimitType;

import java.util.UUID;

public class ItemControllerFixture {

    public static final String ITEM_DEFAULT_URL = "/api/v1/item";
    public static final UUID 프로덕트_아이디 = UlidCreator.getMonotonicUlid().toUuid();
    public static final String 아이템_이름 = "No.1";
    public static final int 아이템_가격 = 10_000;
    public static final int 아이템_수량 = 100;
    public static final LimitType 아이템_수량_한정_여부 = LimitType.LIMITED;

    public static ItemCommand.RegisterRequest ITEM_등록_요청() {
        return ItemCommand.RegisterRequest.builder()
            .productId(프로덕트_아이디)
            .name(아이템_이름)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .build();
    }
}
