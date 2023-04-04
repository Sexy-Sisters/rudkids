package com.rudkids.rudkids.domain.notification;

import org.springframework.stereotype.Service;

public interface NotificationService {
    void sendEmail(String email, String title, String description);
    void sendKakao(String phoneNo, String description);
    void sendSms(String phoneNo, String description);
}
