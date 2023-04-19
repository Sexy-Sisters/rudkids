package com.rudkids.rudkids.domain.order.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayMethod {
    TOSS("토스페이"),
    KAKAO("카카오페이"),
    NAVER("네이버페이"),
    PAYPAL("페이팔"),
    ;

    private final String description;
}
