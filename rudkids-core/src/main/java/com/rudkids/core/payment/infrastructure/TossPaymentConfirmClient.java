package com.rudkids.core.payment.infrastructure;

import com.rudkids.core.payment.dto.TossPaymentRequest;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TossPaymentConfirmClient", url = "https://api.tosspayments.com/v1/payments/confirm")
public interface TossPaymentConfirmClient {

    @PostMapping
    void post(
        @RequestHeader HttpHeaders headers,
        @RequestBody TossPaymentRequest.Confirm request
    ) throws FeignException;
}
