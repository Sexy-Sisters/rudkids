package com.rudkids.core.order.infrastructure;

import com.rudkids.core.admin.dto.DeliveryTrackResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DeliveryTracker", url = "https://apis.tracker.delivery/carriers/kr.cupost/tracks")
public interface DeliveryStateTrackerClient {

    @GetMapping("/{id}")
    DeliveryTrackResponse.Info get(@PathVariable("id") String trackId) throws FeignException;
}