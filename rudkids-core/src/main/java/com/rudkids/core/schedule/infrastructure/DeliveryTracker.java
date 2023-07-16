package com.rudkids.core.schedule.infrastructure;

import com.rudkids.core.schedule.dto.DeliveryTrackResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DeliveryTracker", url = "https://apis.tracker.delivery/carriers/kr.cupost/tracks")
public interface DeliveryTracker {

    @GetMapping("/{id}")
    DeliveryTrackResponse.Info get(@PathVariable("id") String trackId) throws FeignException;
}