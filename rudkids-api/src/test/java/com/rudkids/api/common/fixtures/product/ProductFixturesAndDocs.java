package com.rudkids.api.common.fixtures.product;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ProductFixturesAndDocs {

    public static final String PRODUCT_DEFAULT_URL = "/api/v1/product";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();

    public static ProductResponse.Main PRODUCT_MAIN_INFO() {
        return new ProductResponse.Main(
            프로덕트_아이디,
            "프로덕트 제목",
            "https://~",
            "https://~"
        );
    }

    public static Page<ProductResponse.Main> PRODUCT_리스트_조회_응답() {
        return new PageImpl<>(List.of(PRODUCT_MAIN_INFO()));
    }

    public static List<FieldDescriptor> PRODUCT_리스트_응답_필드() {
        return pageResponseFieldsWith(
            List.of(
                fieldWithPath("content[].productId")
                    .type(JsonFieldType.STRING)
                    .description("프로덕트 아이디"),

                fieldWithPath("content[].title")
                    .type(JsonFieldType.STRING)
                    .description("매거진 제목"),

                fieldWithPath("content[].frontImageUrl")
                    .type(JsonFieldType.STRING)
                    .description("프로덕트 앞 이미지"),

                fieldWithPath("content[].backImageUrl")
                    .type(JsonFieldType.STRING)
                    .description("프로덕트 뒤 이미지")
            )
        );
    }

    public static ProductResponse.Detail PRODUCT_상세조회_INFO() {
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
