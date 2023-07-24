package com.rudkids.api.common.fixtures.item;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.LimitType;
import com.rudkids.core.item.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.UUID;

import static com.rudkids.api.common.ControllerTest.pageResponseFieldsWith;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ItemFixturesAndDocs {

    public static final String ITEM_DEFAULT_URL = "/api/v1/item";
    public static final UUID 아이템_아이디 = UUID.randomUUID();
    public static final String 아이템_영어_이름 = "No.1";
    public static final String 아이템_한국_이름 = "남바완";
    public static final String 아이템_소개글 = "소개합니다~~~~";
    public static final int 아이템_가격 = 10_000;
    public static final int 아이템_수량 = 100;
    public static final LimitType 아이템_수량_한정_여부 = LimitType.LIMITED;
    public static final List<String> 아이템_여러_이미지 = List.of("url1", "url2");

    public static ItemResponse.Detail ITEM_상세정보_조회_응답() {
        return ItemResponse.Detail.builder()
            .enName(아이템_영어_이름)
            .koName(아이템_한국_이름)
            .price(아이템_가격)
            .itemBio(아이템_소개글)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(List.of(new ItemResponse.DetailImage("path", "url", 1)))
            .itemStatus(ItemStatus.SELLING)
            .itemOptionGroupInfoList(ITEM_OPTION_GROUP())
            .grayImage(new ImageResponse.Info("path", "url"))
            .build();
    }

    public static List<ItemResponse.DetailOptionGroup> ITEM_OPTION_GROUP() {
        return List.of(
            new ItemResponse.DetailOptionGroup(
                "지속시간",
                List.of(new ItemResponse.DetailOption("name", 3000, 1)),
                1
            )
        );
    }

    public static Page<ItemResponse.Main> ITEM_아이템_응답() {
        return new PageImpl<>(List.of(
            new ItemResponse.Main(UUID.randomUUID(), "name", 1000, 아이템_여러_이미지, ItemStatus.SELLING))
        );
    }

    public static List<FieldDescriptor> ITEM_리스트_응답_필드() {
        return pageResponseFieldsWith(
            List.of(
                fieldWithPath("content[]itemId")
                    .type(JsonFieldType.STRING)
                    .description("상품 id"),

                fieldWithPath("content[]name")
                    .type(JsonFieldType.STRING)
                    .description("상품 이름"),

                fieldWithPath("content[]price")
                    .type(JsonFieldType.NUMBER)
                    .description("상품 가격"),

                fieldWithPath("content[]imageUrls")
                    .type(JsonFieldType.ARRAY)
                    .description("상품 이미지들"),

                fieldWithPath("content[]itemStatus")
                    .type(JsonFieldType.STRING)
                    .description("상품 상태")
            )
        );
    }
}
