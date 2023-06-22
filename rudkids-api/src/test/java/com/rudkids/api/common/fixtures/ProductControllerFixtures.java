package com.rudkids.api.common.fixtures;

import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.product.dto.ProductRequest;
import com.rudkids.core.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

public class ProductControllerFixtures {

    public static final String PRODUCT_DEFAULT_URL = "/api/v1/product";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();
    public static final String 프로덕트_제목 = "Strange Drugstore";
    public static final String 프로덕트_소개글 = "약쟁이가 약팝니다~~~~";
    public static final String 프로덕트_앞_이미지 = "https://~";
    public static final String 프로덕트_뒤_이미지 = "https://~";
    public static final List<String> 프로덕트_배너_이미지들 = List.of("https://", "https://");
    public static final ProductStatus 프로덕트_상태 = ProductStatus.OPEN;

    public static ProductRequest.Create PRODUCT_등록_요청() {
        return new ProductRequest.Create(프로덕트_제목, 프로덕트_소개글, 이미지(), 이미지(), 배너이미지());
    }

    private static ImageRequest.Create 이미지() {
        return new ImageRequest.Create("image", "image.jpg");
    }

    private static List<ImageRequest.Create> 배너이미지() {
        return List.of(
            new ImageRequest.Create("image", "image.jpg")
        );
    }

    public static ProductResponse.Main PRODUCT_MAIN_INFO() {
        return new ProductResponse.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_앞_이미지,
            프로덕트_뒤_이미지,
            프로덕트_상태
        );
    }

    public static Page<ProductResponse.Main> PRODUCT_MAIN_RESPONSES() {
        return new PageImpl<>(List.of(new ProductResponse.Main(
            프로덕트_아이디,
            프로덕트_제목,
            프로덕트_앞_이미지,
            프로덕트_뒤_이미지,
            프로덕트_상태
        )));
    }

    public static Page<ProductResponse.Main> PRODUCT_리스트_조회_응답() {
        return new PageImpl<>(List.of(PRODUCT_MAIN_INFO()));
    }

    public static ProductResponse.Detail PRODUCT_상세조회_INFO() {
        return ProductResponse.Detail.builder()
            .title(프로덕트_제목)
            .bio(프로덕트_소개글)
            .frontImageUrl(프로덕트_앞_이미지)
            .backImageUrl(프로덕트_뒤_이미지)
            .bannerImageUrls(프로덕트_배너_이미지들)
            .items(
                new PageImpl<>(List.of(
                    ITEM_리스트_조회_INFO()
                ))
            )
            .build();
    }

    public static List<ProductResponse.Search> PRODUCT_검색_INFO_응답() {
        return List.of(
            new ProductResponse.Search(프로덕트_아이디, 프로덕트_제목, 프로덕트_앞_이미지),
            new ProductResponse.Search(프로덕트_아이디, 프로덕트_제목, 프로덕트_앞_이미지)
        );
    }

    private static ItemResponse.Main ITEM_리스트_조회_INFO() {
        return ItemResponse.Main.builder()
            .itemId(UUID.randomUUID())
            .name("아이템")
            .price(1000)
            .imageUrls(List.of("url1", "url2", "url3"))
            .itemStatus(ItemStatus.SELLING)
            .build();
    }

    public static ProductRequest.Update PRODUCT_수정_요청() {
        return new ProductRequest.Update(프로덕트_제목, 프로덕트_소개글, 이미지(), 이미지());
    }

    public static ProductRequest.ChangeStatus PRODUCT_상태_변경_요청() {
        return new ProductRequest.ChangeStatus("CLOSED");
    }
}
