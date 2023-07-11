package com.rudkids.api.product;

import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.product.dto.ProductRequest;
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

    public static ProductRequest.Create PRODUCT_등록_요청() {
        return new ProductRequest.Create("프로덕트 제목", "설명", 이미지(), 이미지(), 배너이미지(), "TOY");
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
            "프로덕트 제목",
            "https://~",
            "https://~",
            ProductStatus.OPEN
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
                    .description("프로덕트 뒤 이미지"),

                fieldWithPath("content[].status")
                    .type(JsonFieldType.STRING)
                    .description("프로덕트 상태")
            )
        );
    }

    public static ProductResponse.Detail PRODUCT_상세조회_INFO() {
        return ProductResponse.Detail.builder()
            .title("프로덕트 제목")
            .bio("설명")
            .frontImage(new ImageResponse.Info("https://~", "https://~"))
            .backImage(new ImageResponse.Info("https://~", "https://~"))
            .bannerImages(List.of(new ImageResponse.Info("https://~", "https://~")))
            .items(
                new PageImpl<>(List.of(
                    ITEM_리스트_조회_INFO()
                ))
            )
            .build();
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
        return new ProductRequest.Update("프로덕트 제목", "설명", 이미지(), 이미지(), "TOY");
    }

    public static ProductRequest.ChangeStatus PRODUCT_상태_변경_요청() {
        return new ProductRequest.ChangeStatus("CLOSED");
    }
}
