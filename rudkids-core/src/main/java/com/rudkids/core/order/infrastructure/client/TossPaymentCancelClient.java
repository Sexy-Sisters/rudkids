package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.config.TossHeaderConfig;
import com.rudkids.core.order.infrastructure.dto.TossPaymentRequest;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TossPaymentCancelClient", url = "https://api.tosspayments.com/v1/payments/", configuration = {TossHeaderConfig.class})
public interface TossPaymentCancelClient {

    @PostMapping("/{paymentKey}/cancel")
    void cancel(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody TossPaymentRequest.Cancel request
    ) throws FeignException;

    @PostMapping("/{paymentKey}/cancel")
    void cancelVirtualAccount(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody TossPaymentRequest.CancelVirtualAccount request
    ) throws FeignException;
}
