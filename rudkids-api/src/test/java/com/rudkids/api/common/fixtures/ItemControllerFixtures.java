package com.rudkids.api.common.fixtures;

import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.dto.ItemRequest;
import com.rudkids.core.item.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ItemControllerFixtures {

    public static final String ITEM_DEFAULT_URL = "/api/v1/item";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();
    public static final UUID 아이템_아이디 = UUID.randomUUID();
    public static final String 아이템_영어_이름 = "No.1";
    public static final String 아이템_한국_이름 = "남바완";
    public static final String 아이템_소개글 = "소개합니다~~~~";
    public static final int 아이템_가격 = 10_000;
    public static final int 아이템_수량 = 100;
    public static final LimitType 아이템_수량_한정_여부 = LimitType.LIMITED;
    public static final String 아이템_상태 = "SELLING";
    public static final List<String> 아이템_여러_이미지 = List.of("url1", "url2");

    private static List<ImageRequest.Create> 아이템_여러_이미지_수정_요청() {
        return List.of(
            new ImageRequest.Create("image", "image.jpg")
        );
    }

    public static ItemRequest.Create ITEM_등록_요청() {
        return ItemRequest.Create.builder()
            .enName(아이템_영어_이름)
            .koName(아이템_한국_이름)
            .itemBio(아이템_소개글)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(List.of(
                new ImageRequest.Create("path", "url"),
                new ImageRequest.Create("path", "url")
            ))
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .videoImage(new ImageRequest.Create("path", "url"))
            .videoUrl("비디오 url")
            .build();
    }

    private static final ItemRequest.CreateItemOptionGroup itemOptionGroup_사이즈 = ItemRequest.CreateItemOptionGroup.builder()
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.CreateItemOption.builder()
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

    public static ItemResponse.Detail ITEM_상세정보_조회_응답() {
        return ItemResponse.Detail.builder()
            .enName(아이템_영어_이름)
            .koName(아이템_한국_이름)
            .price(아이템_가격)
            .itemBio(아이템_소개글)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(List.of(new ImageResponse.Info("path", "url")))
            .itemStatus(ItemStatus.SELLING)
            .videoUrl("영상 url")
            .build();
    }

    public static ItemRequest.ChangeStatus ITEM_상태_변경_요청() {
        return new ItemRequest.ChangeStatus("SOLD_OUT");
    }

    public static ItemRequest.Update ITEM_수정_요청() {
        return ItemRequest.Update.builder()
            .enName(아이템_영어_이름)
            .koName(아이템_한국_이름)
            .itemBio(아이템_소개글)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(아이템_여러_이미지_수정_요청())
            .build();
    }

    public static Page<ItemResponse.VideoImage> ITEM_영상_이미지_응답() {
        return new PageImpl<>(List.of(
            new ItemResponse.VideoImage(아이템_영어_이름, new ImageResponse.Info("path", "url"))
        ));
    }

    public static ItemResponse.Video ITEM_영상_응답() {
        return new ItemResponse.Video(아이템_영어_이름, "url");
    }

    public static List<FieldDescriptor> ITEM_영상_이미지_응답_필드() {
        return pageResponseFieldsWith(
            List.of(
                fieldWithPath("content[]name")
                    .type(JsonFieldType.STRING)
                    .description("상품 영어 이름"),

                fieldWithPath("content[]image")
                    .type(JsonFieldType.OBJECT)
                    .description("상품 영상 이미지"),

                fieldWithPath("content[]image.path")
                    .type(JsonFieldType.STRING)
                    .description("상품 영상 이미지 path"),

                fieldWithPath("content[]image.url")
                    .type(JsonFieldType.STRING)
                    .description("상품 영상 이미지 url")
            )
        );
    }
}
