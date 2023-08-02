package com.rudkids.core.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VirtualAccount {

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private BankCode bankCode;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "refund_bank_code")
    private String refundBankCode;

    @Column(name = "refund_account_name")
    private String refundAccountName;

    @Column(name = "refund_holder_name")
    private String refundHolderName;

    @Builder
    private VirtualAccount(String accountNumber,
                          BankCode bankCode,
                          String customerName,
                          String dueDate,
                           String refundBankCode,
                           String refundAccountName,
                           String refundHolderName
    ) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.customerName = customerName;
        this.dueDate = dueDate;
        this.refundBankCode = refundBankCode;
        this.refundAccountName = refundAccountName;
        this.refundHolderName = refundHolderName;
    }

    public static VirtualAccount createDefault() {
        return new VirtualAccount("", BankCode.EMPTY, "", "", "", "", "");
    }

    public String getBankName() {
        return bankCode.getName();
    }

    public boolean isExpireDueDate() {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        var parsedDueDate = ZonedDateTime.parse(dueDate);
        return currentDateTime.isAfter(parsedDueDate);
    }
}
