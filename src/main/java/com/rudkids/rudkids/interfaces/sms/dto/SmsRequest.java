package com.rudkids.rudkids.interfaces.sms.dto;

import net.nurigo.sdk.message.model.Message;

public class SmsRequest {

    public record Send(
            String recipientPhoneNumber,
            String senderPhoneNumber,
            String title,
            String content) {

        public Message toMessage() {
            Message message = new Message();
            message.setFrom(senderPhoneNumber);
            message.setTo(recipientPhoneNumber);
            message.setText(title + "\n" + content);
            return message;
        }
    }
}
