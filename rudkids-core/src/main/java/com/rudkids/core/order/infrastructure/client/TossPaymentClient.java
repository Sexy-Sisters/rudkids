package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.order.domain.BankCode;
import com.rudkids.core.order.domain.Order;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.infrastructure.dto.TossPaymentRequest;
import com.rudkids.core.order.infrastructure.dto.TossPaymentResponse;
import com.rudkids.core.order.exception.PaymentCancelFailException;
import com.rudkids.core.order.exception.PaymentConfirmFailException;
import com.rudkids.core.order.service.PaymentClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPaymentClient implements PaymentClient {
    private final TossPaymentConfirmClient tossPaymentConfirmClient;
    private final TossPaymentCancelClient tossPaymentCancelClient;

    @Override
    public TossPaymentResponse.Info confirm(final String paymentKey, final String orderId, final int amount) {
        var confirmRequest = TossPaymentRequest.Confirm.builder()
            .paymentKey(paymentKey)
            .orderId(orderId)
            .amount(amount)
            .build();

        try {
            return tossPaymentConfirmClient.post(confirmRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new PaymentConfirmFailException();
        }
    }

    @Override
    public void cancelVirtualAccount(Order order, String cancelReason) {
        var cancelRequest = TossPaymentRequest.CancelVirtualAccount.builder()
            .cancelReason(cancelReason)
            .refundReceiveAccount(generateRefundAccount(order))
            .build();

        try {
            tossPaymentCancelClient.cancelVirtualAccount(order.getPaymentKey(), cancelRequest);
        } catch (FeignException e) {
            throw new PaymentCancelFailException();
        }
    }

    private TossPaymentRequest.RefundReceiveAccount generateRefundAccount(Order order) {
        var bankCode = BankCode.toEnumByName(order.getRefundBankName());
        return TossPaymentRequest.RefundReceiveAccount.builder()
            .bank(bankCode.getCode())
            .accountNumber(order.getRefundAccountNumber())
            .holderName(order.getRefundHolderName())
            .build();
    }

    @Override
    public void cancel(String paymentKey, String cancelReason) {
        var request = TossPaymentRequest.Cancel.builder()
            .cancelReason(cancelReason)
            .build();

        try {
            tossPaymentCancelClient.cancel(paymentKey, request);
        } catch (FeignException e) {
            throw new PaymentCancelFailException();
        }
    }
}
