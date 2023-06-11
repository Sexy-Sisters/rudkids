package com.rudkids.rudkids.infrastructure.payment.client;

import com.rudkids.rudkids.domain.payment.PaymentClientManager;
import com.rudkids.rudkids.domain.payment.PaymentCommand;
import com.rudkids.rudkids.global.config.properties.TossPaymentProperties;
import com.rudkids.rudkids.infrastructure.payment.dto.PaymentConfirmRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TossPaymentClientManager implements PaymentClientManager {
    private final TossPaymentProperties properties;
    private final TossConfirmClient tossConfirmClient;

    @Override
    public void confirm(PaymentCommand.Confirm command) {
         requestConfirm(command);
    }

    private void requestConfirm(PaymentCommand.Confirm command) {
        HttpHeaders headers = generateHeader();
        PaymentConfirmRequest request = PaymentConfirmRequest.builder()
            .paymentKey(command.paymentKey())
            .amount(command.amount())
            .orderId(command.orderId())
            .build();

        tossConfirmClient.post(headers, request);
    }

    private HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getSecretKey());
        return headers;
    }
}
