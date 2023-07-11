package com.rudkids.core.order.infrastructure.payment;

import com.rudkids.core.config.properties.TossPaymentProperties;
import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.dto.TossPaymentRequest;
import com.rudkids.core.order.exception.PaymentCancelFailException;
import com.rudkids.core.order.exception.PaymentConfirmFailException;
import com.rudkids.core.order.service.PaymentClientManager;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPaymentClientManager implements PaymentClientManager {
    private final TossPaymentProperties properties;
    private final TossPaymentConfirmClient tossPaymentConfirmClient;
    private final TossPaymentCancelClient tossPaymentCancelClient;

    @Override
    public void confirm(String paymentKey, String orderId, int amount) {
        var confirmRequest = TossPaymentRequest.Confirm.builder()
            .paymentKey(paymentKey)
            .orderId(orderId)
            .amount(amount)
            .build();

        try {
            tossPaymentConfirmClient.post(generateHeader(), confirmRequest);
        } catch (FeignException e) {
            throw new PaymentConfirmFailException();
        }
    }

    @Override
    public void cancel(OrderRequest.Cancel request) {
        var cancelRequest = TossPaymentRequest.Cancel.builder()
            .cancelReason(request.cancelReason())
            .refundReceiveAccount(generateRefundAccount(request))
            .build();

        try {
            tossPaymentCancelClient.cancel(generateHeader(), request.paymentKey(), cancelRequest);
        } catch (FeignException e) {
            throw new PaymentCancelFailException();
        }
    }

    private TossPaymentRequest.RefundReceiveAccount generateRefundAccount(OrderRequest.Cancel request) {
        return TossPaymentRequest.RefundReceiveAccount.builder()
            .bank(request.bankCode())
            .accountNumber(request.refundAccountNumber())
            .holderName(request.refundAccountHolderName())
            .build();
    }

    private HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getSecretKey());
        return headers;
    }
}
