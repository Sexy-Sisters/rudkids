package com.rudkids.rudkids.domain.notification;

import com.rudkids.rudkids.interfaces.notification.dto.NotificationRequest;

public interface NotificationService {
    void sendEmail(String email, String title, String description);
    void sendKakao(String phoneNo, String description);
    void sendSms(NotificationRequest.Send request);
}
