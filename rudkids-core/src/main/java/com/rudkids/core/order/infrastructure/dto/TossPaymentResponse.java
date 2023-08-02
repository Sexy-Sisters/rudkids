package com.rudkids.core.order.infrastructure.dto;

import com.rudkids.core.order.domain.BankCode;
import com.rudkids.core.order.domain.VirtualAccount;

import java.util.Optional;

public class TossPaymentResponse {

    public record Info(
        Optional<VirtualAccountInfo> virtualAccount,
        String method
    ) {
        public VirtualAccount toEntity() {
            return virtualAccount
                .map(account -> VirtualAccount.builder()
                    .accountNumber(account.accountNumber)
                    .bankCode(BankCode.toEnumByCode(account.bankCode))
                    .customerName(account.customerName)
                    .dueDate(account.dueDate)
                    .refundBankCode(account.refundReceiveAccount.bankCode)
                    .refundAccountName(account.refundReceiveAccount.accountNumber)
                    .refundHolderName(account.refundReceiveAccount.holderName)
                    .build()
                )
                .orElseGet(VirtualAccount::createDefault);
        }
    }

    public record VirtualAccountInfo(
        String accountNumber,
        String bankCode,
        String customerName,
        String dueDate,
        RefundReceiveAccountInfo refundReceiveAccount
    ) {}

    public record RefundReceiveAccountInfo(
        String bankCode,
        String accountNumber,
        String holderName
    ) {}
}
