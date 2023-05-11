package com.rudkids.rudkids.infrastructure.notification;

import com.rudkids.rudkids.domain.notification.NotificationService;
import com.rudkids.rudkids.interfaces.notification.dto.NotificationDtoMapper;
import com.rudkids.rudkids.interfaces.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationExecutor implements NotificationService {
    private final DefaultMessageService messageService;
    private final NotificationDtoMapper notificationDtoMapper;

    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("sendEmail");
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("sendKakao");
    }

    @Override
    public void sendSms(NotificationRequest.SendSms request){
        var message = notificationDtoMapper.toMessage(request);
        messageService.sendOne(new SingleMessageSendingRequest(message));
    }
}
