package com.rudkids.rudkids.interfaces.notification;

import com.rudkids.rudkids.domain.notification.NotificationService;
import com.rudkids.rudkids.interfaces.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/sms")
    public void send(@RequestBody NotificationRequest.Send request) {
        notificationService.sendSms(request);
    }
}