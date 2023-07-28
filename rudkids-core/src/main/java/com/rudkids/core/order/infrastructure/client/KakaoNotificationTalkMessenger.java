package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.order.infrastructure.dto.KakaoNotificationTalkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoNotificationTalkMessenger {
    private final WebClient webClient;

    public void sendDeliveryCompleted(String phoneNumber, String accessToken) {
        var request = KakaoNotificationTalkRequest.Send.builder()
            .senderKey("")
            .cid("234341")
            .templateCode("안녕?")
            .phoneNumber(phoneNumber)
            .senderNo("")
            .message("주문완료")
            .build();

        webClient.post()
            .uri("https://bizmsg-web.kakaoenterprise.com/v2/send/kakao")
            .accept(MediaType.ALL)
            .contentType(MediaType.APPLICATION_JSON)
            .header("authorization", "Bearer " + accessToken)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }
}
