package com.rudkids.rudkids.common.fixtures.item;

import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.domain.LimitType;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import com.rudkids.rudkids.interfaces.item.dto.ItemRequest;
import com.rudkids.rudkids.interfaces.item.dto.ItemResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class ItemControllerFixtures {

    public static final String ITEM_DEFAULT_URL = "/api/v1/item";
    public static final UUID 프로덕트_아이디 = UUID.randomUUID();
    public static final UUID 아이템_아이디 = UUID.randomUUID();
    public static final String 아이템_이름 = "No.1";
    public static final String 아이템_소개글 = "소개합니다~~~~";
    public static final int 아이템_가격 = 10_000;
    public static final int 아이템_수량 = 100;
    public static final LimitType 아이템_수량_한정_여부 = LimitType.LIMITED;
    public static final ItemStatus 아이템_상태 = ItemStatus.SELLING;
    public static final List<String> 아이템_여러_이미지 = List.of("url1", "url2");

    private static List<ImageRequest> 아이템_여러_이미지_수정_요청() {
        return List.of(
            new ImageRequest("image", "image.jpg")
        );
    }

    public static ItemRequest.RegisterItem ITEM_등록_요청() {
        return ItemRequest.RegisterItem.builder()
            .name(아이템_이름)
            .itemBio(아이템_소개글)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(List.of(new ImageRequest("path", "url"), new ImageRequest("path", "url")))
            .itemOptionGroupList(List.of(itemOptionGroup_사이즈))
            .build();
    }

    private static final ItemRequest.RegisterItemOptionGroup itemOptionGroup_사이즈 = ItemRequest.RegisterItemOptionGroup.builder()
        .ordering(1)
        .itemOptionGroupName("사이즈")
        .itemOptionList(List.of(
            ItemRequest.RegisterItemOption.builder()
                .ordering(1)
                .itemOptionName("S")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.RegisterItemOption.builder()
                .ordering(2)
                .itemOptionName("M")
                .itemOptionPrice(0)
                .build(),
            ItemRequest.RegisterItemOption.builder()
                .ordering(3)
                .itemOptionName("L")
                .itemOptionPrice(1000)
                .build()
        )).build();

    public static ItemInfo.Detail ITEM_상세정보_조회_응답() {
        return ItemInfo.Detail.builder()
            .name(아이템_이름)
            .price(아이템_가격)
            .itemBio(아이템_소개글)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .imageUrls(아이템_여러_이미지)
            .itemStatus(아이템_상태)
            .build();
    }

    public static ItemResponse.Detail ITEM_상세정보_조회_DTO_응답() {
        return ItemResponse.Detail.builder()
            .name(아이템_이름)
            .price(아이템_가격)
            .itemBio(아이템_소개글)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .imageUrls(아이템_여러_이미지)
            .itemStatus(아이템_상태)
            .build();
    }

    public static ItemRequest.ChangeStatus ITEM_상태_변경_요청() {
        return new ItemRequest.ChangeStatus(ItemStatus.SOLD_OUT);
    }

    public static ItemRequest.Update ITEM_수정_요청() {
        return ItemRequest.Update.builder()
            .name(아이템_이름)
            .itemBio(아이템_소개글)
            .price(아이템_가격)
            .quantity(아이템_수량)
            .limitType(아이템_수량_한정_여부)
            .images(아이템_여러_이미지_수정_요청())
            .build();
    }
}
