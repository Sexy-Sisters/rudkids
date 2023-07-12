package com.rudkids.api.delivery;

import com.rudkids.core.delivery.dto.DeliveryRequest;
import com.rudkids.core.delivery.dto.DeliveryResponse;

import java.util.List;
import java.util.UUID;

public class DeliveryFixturesAndDocs {

    public static final String DELIVERY_DEFAULT_URL = "/api/v1/delivery";
    public static final UUID DELIVERY_ID = UUID.randomUUID();

    public static DeliveryRequest.Create DELIVERY_등록_요청() {
        return new DeliveryRequest.Create(
            "남세원",
            "01012345678",
            "45387",
            "부산광역시 강서구 가락대로",
            "행정실 문 앞",
            "도착하면 연락해주세요",
            true
        );
    }

    public static DeliveryRequest.Update DELIVERY_수정_요청() {
        return new DeliveryRequest.Update(
            "홍길동",
            "01098765432",
            "212421",
            "인천 송도",
            "문 앞",
            "도착하면 연락해주세요"
        );
    }

    public static List<DeliveryResponse.Info> DELIVERY_리스트_응답() {
        return List.of(
            new DeliveryResponse.Info(
                DELIVERY_ID,
                "남세원",
                "01012345678",
                "45387",
                "부산광역시 강서구 가락대로",
                "행정실 문 앞",
                "도착하면 연락해주세요",
                true
            )
        );
    }

    public static DeliveryResponse.Info DELIVERY_상세조회_응답() {
        return new DeliveryResponse.Info(
            DELIVERY_ID,
            "남세원",
            "01012345678",
            "45387",
            "부산광역시 강서구 가락대로",
            "행정실 문 앞",
            "도착하면 연락해주세요",
            true
        );
    }
}
