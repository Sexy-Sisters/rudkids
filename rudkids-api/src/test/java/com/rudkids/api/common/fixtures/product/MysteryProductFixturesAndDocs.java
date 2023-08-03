package com.rudkids.api.common.fixtures.product;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.dto.ProductResponse;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

public class MysteryProductFixturesAndDocs {

    public static final String MYSTERY_PRODUCT_DEFAULT_URL = "/api/v1/product/mystery";
    public static final UUID 미스테리_프로덕트_아이디 = UUID.randomUUID();

    public static ProductResponse.Main 미스테리_프로덕트_응답() {
        return new ProductResponse.Main(미스테리_프로덕트_아이디, "title", "url", "url");
    }

    public static ProductResponse.Detail 미스테리_프로덕트_상세_응답() {
        return ProductResponse.Detail.builder()
            .title("프로덕트 제목")
            .bio("설명")
            .frontImage(new ImageResponse.Info("https://~", "https://~"))
            .backImage(new ImageResponse.Info("https://~", "https://~"))
            .bannerImage(new ImageResponse.Info("https://~", "https://~"))
            .items(
                new PageImpl<>(List.of(
                    ITEM_리스트_조회_INFO()
                ))
            )
            .mobileImage(new ImageResponse.Info("https://~", "https://~"))
            .build();
    }

    private static ItemResponse.Main ITEM_리스트_조회_INFO() {
        return ItemResponse.Main.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .imageUrl("url")
            .itemStatus(ItemStatus.SELLING)
            .build();
    }
}
