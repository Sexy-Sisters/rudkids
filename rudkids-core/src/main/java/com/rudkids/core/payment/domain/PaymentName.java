package com.rudkids.core.payment.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@Getter
@NoArgsConstructor
public class PaymentName {
    private static final String ORDER_NAME_DELIMITER = ",";
    private static final String PAYMENT_NAME_FORMAT = " 외 {0}건";

    private String name;

    public PaymentName(String name) {
        this.name = name;
    }

    public static PaymentName create(String name, int count) {
        var itemName = split(name);
        var paymentName = generateName(count);
        return new PaymentName(itemName + paymentName);
    }

    private static String split(String name) {
        return name.split(ORDER_NAME_DELIMITER)[0];
    }

    private static String generateName(int count) {
        return MessageFormat.format(PAYMENT_NAME_FORMAT, count);
    }
}
