package com.rudkids.rudkids.interfaces.notification.dto;

public class NotificationRequest {

    public record Send(
        String recipientPhoneNumber,
        String senderPhoneNumber,
        String title,
        String content
    ) {
    }
}
