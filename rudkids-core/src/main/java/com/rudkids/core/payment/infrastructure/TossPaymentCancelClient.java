package com.rudkids.core.payment.infrastructure;

import com.rudkids.core.config.FeignHeaderConfig;
import com.rudkids.core.payment.dto.TossPaymentRequest;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TossPaymentCancelClient", url = "https://api.tosspayments.com/v1/payments/", configuration = {FeignHeaderConfig.class})
public interface TossPaymentCancelClient {

    @PostMapping("/{paymentKey}/cancel")
    void cancel(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody TossPaymentRequest.Cancel request
    ) throws FeignException;
}
