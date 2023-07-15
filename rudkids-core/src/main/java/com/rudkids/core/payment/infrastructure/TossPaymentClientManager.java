package com.rudkids.core.payment.infrastructure;

import com.rudkids.core.config.properties.TossPaymentProperties;
import com.rudkids.core.payment.exception.PaymentCancelFailException;
import com.rudkids.core.payment.exception.PaymentConfirmFailException;
import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.dto.TossPaymentRequest;
import com.rudkids.core.payment.service.PaymentClientManager;
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
    public void confirm(PaymentRequest.Confirm request) {
        var confirmRequest = TossPaymentRequest.Confirm.builder()
            .paymentKey(request.paymentKey())
            .orderId(request.orderId().toString())
            .amount(request.amount())
            .build();

        try {
            System.out.println("secret key = " + properties.getSecretKey());
            tossPaymentConfirmClient.post("Basic " + properties.getSecretKey(), confirmRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new PaymentConfirmFailException();
        }
    }

    @Override
    public void cancel(PaymentRequest.Cancel request) {
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

    private TossPaymentRequest.RefundReceiveAccount generateRefundAccount(PaymentRequest.Cancel request) {
        return TossPaymentRequest.RefundReceiveAccount.builder()
            .bank(request.bankCode())
            .accountNumber(request.refundAccountNumber())
            .holderName(request.refundAccountHolderName())
            .build();
    }

    private HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + properties.getSecretKey());
        return headers;
    }
}
