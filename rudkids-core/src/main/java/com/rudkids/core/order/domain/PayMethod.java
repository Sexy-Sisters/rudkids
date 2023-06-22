package com.rudkids.core.order.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayMethod {
    TOSS("토스페이"),
    KAKAO("카카오페이"),
    NAVER("네이버페이"),
    PAYPAL("페이팔"),
    ;

    private final String description;
}
