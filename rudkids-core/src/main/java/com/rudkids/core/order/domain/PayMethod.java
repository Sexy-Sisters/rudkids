package com.rudkids.core.order.domain;


import com.rudkids.core.payment.exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PayMethod {
    CART("카드"),
    NAVER("네이버페이"),
    KAKAO("카카오페이"),
    PAYPCO("페이코"),
    TOSS("토스페이"),
    VIRTUAL_ACCOUNT("가상계좌"),
    PHONE("휴대폰"),
    ACCOUNT_TRANSFER("계좌이체"),
    SAMSUNG("삼성페이");

    private final String description;

    public static PayMethod validate(PayMethod payMethod) {
        return Arrays.stream(values())
            .filter(it -> it.equals(payMethod))
            .findFirst()
            .orElseThrow(PaymentNotFoundException::new);
    }
}
