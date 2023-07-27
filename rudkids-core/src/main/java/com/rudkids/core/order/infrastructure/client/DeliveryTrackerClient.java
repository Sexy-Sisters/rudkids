package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.admin.dto.DeliveryTrackResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DeliveryTrackerClient", url = "https://apis.tracker.delivery/carriers/kr.cjlogistics/tracks")
public interface DeliveryTrackerClient {

    @GetMapping("/{id}")
    DeliveryTrackResponse.Info get(@PathVariable("id") String trackId) throws FeignException;
}