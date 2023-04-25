package com.rudkids.rudkids.common.fixtures.product;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.product.ProductInfo;
import com.rudkids.rudkids.interfaces.product.dto.ProductRequest;
import com.rudkids.rudkids.interfaces.product.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public class ProductControllerFixtures {

    public static final String PRODUCT_DEFAULT_URL = "/api/v1/product";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();
    public static final String 프로덕트_제목 = "Strange Drugstore";
    public static final String 프로덕트_소개글 = "약쟁이가 약팝니다~~~~";

    public static ProductRequest.Register PRODUCT_등록_요청() {
        return new ProductRequest.Register(프로덕트_제목, 프로덕트_소개글);
    }

    public static ProductInfo.Main PRODUCT_MAIN_INFO() {
        return new ProductInfo.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_소개글
        );
    }

    public static ProductResponse.Main PRODUCT_MAIN_RESPONSE() {
        return new ProductResponse.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_소개글
        );
    }

    public static List<ProductInfo.Main> PRODUCT_리스트_조회_응답() {
        return List.of(
            PRODUCT_MAIN_INFO()
        );
    }

    public static ProductInfo.Detail PRODUCT_상세조회_INFO() {
        return ProductInfo.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .items(
                List.of(ITEM_리스트_조회_INFO())
            )
            .build();
    }

    private static ProductInfo.ProductItem ITEM_리스트_조회_INFO() {
        return ProductInfo.ProductItem.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .itemStatus(ItemStatus.SELLING)
            .build();
    }

    public static ProductResponse.Detail PRODUCT_상세조회_RESPONSE() {
        return ProductResponse.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .items(
                List.of(ITEM_리스트_조회_RESPONSE())
            )
            .build();
    }

    private static ProductResponse.ProductItem ITEM_리스트_조회_RESPONSE() {
        return ProductResponse.ProductItem.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .itemStatus(ItemStatus.SELLING)
            .build();
    }
}
