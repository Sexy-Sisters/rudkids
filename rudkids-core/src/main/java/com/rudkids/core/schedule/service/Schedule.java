package com.rudkids.core.schedule.service;

import com.rudkids.core.order.domain.OrderDelivery;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.schedule.domain.DeliveryTrackState;
import com.rudkids.core.schedule.dto.DeliveryTrackResponse;
import com.rudkids.core.schedule.exception.DeliveryTrackingFailException;
import com.rudkids.core.schedule.infrastructure.DeliveryTracker;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Schedule {
    private final OrderRepository orderRepository;
    private final DeliveryTracker deliveryTracker;

    //돌아가는지 테스트하기
//    @Scheduled(cron = "0 0 * * * *")
    public void checkDeliveryStatusAndSendMessage() {
        log.info("스케쥴링 시작");
        var deliveries = orderRepository.getStatusIngOrderDeliveries();

        for(OrderDelivery delivery: deliveries) {
            var response = getDeliveryInfo(delivery.getTrackingNumber());

            var trackState = DeliveryTrackState.create(response.getState());
            if(trackState.isCompleteState()) {
                delivery.changeStatusToComp();
            }

            //카톡채널로 배송이 완료되었습니다 문자보내기
        }
    }

    private DeliveryTrackResponse.Info getDeliveryInfo(String trackingNumber) {
        try {
            return deliveryTracker.get(trackingNumber);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new DeliveryTrackingFailException();
        }
    }
}
