package com.rudkids.core.payment.infrastructure;

import com.rudkids.core.config.properties.TossPaymentProperties;
import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.infrastructure.dto.PaymentConfirmRequest;
import com.rudkids.core.payment.service.PaymentClientManager;
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
    private final TossConfirmClient tossConfirmClient;

    @Override
    public void confirm(PaymentRequest.Confirm request) {
         requestConfirm(request);
    }

    private void requestConfirm(PaymentRequest.Confirm request) {
        HttpHeaders headers = generateHeader();
        PaymentConfirmRequest confirmRequest = PaymentConfirmRequest.builder()
            .paymentKey(request.paymentKey())
            .amount(request.amount())
            .orderId(request.orderId())
            .build();

        tossConfirmClient.post(headers, confirmRequest);
    }

    private HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getSecretKey());
        return headers;
    }
}
