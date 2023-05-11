package com.rudkids.rudkids.interfaces.notification.dto;

public class NotificationRequest {

    public record SendSms(
        String recipientPhoneNumber,
        String senderPhoneNumber,
        String title,
        String content
    ) {
    }
}
