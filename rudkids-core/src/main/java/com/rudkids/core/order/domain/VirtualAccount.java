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

    @Builder
    private VirtualAccount(String accountNumber,
                          BankCode bankCode,
                          String customerName,
                          String dueDate) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.customerName = customerName;
        this.dueDate = dueDate;
    }

    public static VirtualAccount createDefault() {
        return new VirtualAccount("", BankCode.EMPTY, "", "");
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
