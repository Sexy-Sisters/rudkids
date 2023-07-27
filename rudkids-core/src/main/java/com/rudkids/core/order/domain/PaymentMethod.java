package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.PaymentMethodNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CARD("카드"),
    VIRTUAL_ACCOUNT("가상계좌"),
    PAY("간편결제"),
    PHONE("휴대폰"),
    ACCOUNT_TRANSFER("계좌이체");

    private final String description;

    public static PaymentMethod toEnum(String description) {
        return Arrays.stream(values())
            .filter(method -> method.isSameDescription(description))
            .findFirst()
            .orElseThrow(PaymentMethodNotFoundException::new);
    }

    private boolean isSameDescription(String target) {
        return description.equals(target);
    }

    public boolean isVirtualAccount() {
        return this == VIRTUAL_ACCOUNT;
    }
}
