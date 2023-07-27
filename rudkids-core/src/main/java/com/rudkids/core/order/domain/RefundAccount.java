package com.rudkids.core.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefundAccount {

    private String accountNumber;
    private String bankCode;
    private String accountHolderName;

    private RefundAccount(String accountNumber, String bankCode, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.accountHolderName = accountHolderName;
    }

    public static RefundAccount create(String accountNumber, String bankCode, String accountHolderName) {
        return new RefundAccount(accountNumber, bankCode, accountHolderName);
    }
}
