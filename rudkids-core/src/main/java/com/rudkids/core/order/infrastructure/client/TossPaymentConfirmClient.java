package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.config.TossHeaderConfig;
import com.rudkids.core.order.infrastructure.dto.TossPaymentRequest;
import com.rudkids.core.order.infrastructure.dto.TossPaymentResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TossPaymentConfirmClient", url = "https://api.tosspayments.com/v1/payments/confirm", configuration = {TossHeaderConfig.class})
public interface TossPaymentConfirmClient {

    @PostMapping
    TossPaymentResponse.Info post(@RequestBody TossPaymentRequest.Confirm request) throws FeignException;
}
