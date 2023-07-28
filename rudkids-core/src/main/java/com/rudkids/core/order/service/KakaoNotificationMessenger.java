package com.rudkids.core.order.service;

import com.rudkids.core.auth.domain.OAuthTokenRepository;
import com.rudkids.core.auth.infrastructure.client.kakao.KakaoOAuthClientManager;
import com.rudkids.core.order.infrastructure.client.KakaoNotificationTalkMessenger;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoNotificationMessenger implements NotificationMessenger {
    private final KakaoOAuthClientManager kakaoOAuthClientManager;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final KakaoNotificationTalkMessenger kakaoNotificationTalkMessenger;

    @Override
    public void sendOrderHistory(User user) {

    }

    @Override
    public void sendDeliveryCompleted(User user) {
        var oauthToken = oAuthTokenRepository.get(user);
        var renewalToken = kakaoOAuthClientManager.getRenewalToken(oauthToken.getKakaoRefreshToken());
        oauthToken.change(renewalToken.getRefreshToken());

        kakaoNotificationTalkMessenger.sendDeliveryCompleted(user.getPhoneNumber(), renewalToken.getAccessToken());
    }

    @Override
    public void sendPaymentCancel() {

    }
}
