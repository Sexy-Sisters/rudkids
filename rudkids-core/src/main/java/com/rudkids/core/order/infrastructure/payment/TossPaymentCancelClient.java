package com.rudkids.core.order.infrastructure.payment;

import com.rudkids.core.order.dto.TossPaymentRequest;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TossPaymentCancelClient", url = "https://api.tosspayments.com/v1/payments/")
public interface TossPaymentCancelClient {

    @PostMapping("/{paymentKey}/cancel")
    void cancel(
        @RequestHeader HttpHeaders headers,
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody TossPaymentRequest.Cancel request
    ) throws FeignException;
}
