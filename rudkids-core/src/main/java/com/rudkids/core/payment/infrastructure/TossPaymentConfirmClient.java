package com.rudkids.core.payment.infrastructure;

import com.rudkids.core.payment.dto.TossPaymentRequest;
import feign.FeignException;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TossPaymentConfirmClient", url = "https://api.tosspayments.com/v1/payments/confirm")
public interface TossPaymentConfirmClient {

    @PostMapping
    @Headers({
        "Authorization: {authorization}"
    })
    void post(
        @Param("authorization") String authorization,
        @RequestBody TossPaymentRequest.Confirm request
    ) throws FeignException;
}
