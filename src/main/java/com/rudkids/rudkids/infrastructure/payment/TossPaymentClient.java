package com.rudkids.rudkids.infrastructure.payment;

import com.rudkids.rudkids.domain.payment.PaymentClient;
import com.rudkids.rudkids.domain.payment.PaymentCommand;
import com.rudkids.rudkids.domain.payment.exception.PaymentConfirmException;
import com.rudkids.rudkids.global.config.properties.TossPaymentProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class TossPaymentClient implements PaymentClient {
    private final TossPaymentProperties properties;
    private final RestTemplate restTemplate;

    @Override
    public void confirm(PaymentCommand.Confirm command) {
         requestConfirm(command);
    }

    private void requestConfirm(PaymentCommand.Confirm command) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getSecretKey());
        MultiValueMap<String, Object> params = generateConfirmParams(command);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        paymentConfirm(request);
    }

    private MultiValueMap<String, Object> generateConfirmParams(PaymentCommand.Confirm command) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("paymentKey", command.paymentKey());
        params.add("amount", command.amount());
        params.add("orderId", command.orderId());
        return params;
    }

    private void paymentConfirm(HttpEntity<MultiValueMap<String, Object>> request) {
        try {
            restTemplate.postForObject(properties.getConfirmUri(), request, String.class);
        } catch(RestClientException e) {
            log.error(e.getMessage());
            throw new PaymentConfirmException();
        }
    }
}
