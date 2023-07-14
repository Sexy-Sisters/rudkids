package com.rudkids.core.order.domain;


import com.rudkids.core.payment.exception.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PayMethod {
    TOSS("토스페이"),
    KAKAO("카카오페이"),
    NAVER("네이버페이"),
    PAYPCO("페이코");

    private final String description;

    public static PayMethod validate(PayMethod payMethod) {
        return Arrays.stream(values())
            .filter(it -> it.equals(payMethod))
            .findFirst()
            .orElseThrow(PaymentNotFoundException::new);
    }
}
