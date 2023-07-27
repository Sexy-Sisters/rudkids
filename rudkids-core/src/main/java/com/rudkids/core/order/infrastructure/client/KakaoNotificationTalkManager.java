package com.rudkids.core.order.infrastructure.client;

import com.rudkids.core.order.infrastructure.dto.KakaoNotificationTalkRequest;
import com.rudkids.core.order.service.KakaoNotificationTalkMessenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoNotificationTalkManager implements KakaoNotificationTalkMessenger {
    private final WebClient webClient;

    @Override
    public void sendDeliveryCompleted(String accessToken) {
        var request = KakaoNotificationTalkRequest.Send.builder()
            .senderKey("")
            .cid("")
            .templateCode("")
            .phoneNumber("")
            .senderNo("")
            .message("")
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
