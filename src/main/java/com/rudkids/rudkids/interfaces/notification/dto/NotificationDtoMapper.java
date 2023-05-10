package com.rudkids.rudkids.interfaces.notification.dto;

import net.nurigo.sdk.message.model.Message;
import org.springframework.stereotype.Component;

@Component
public class NotificationDtoMapper {

    public Message toMessage(NotificationRequest.Send request) {
        Message message = new Message();
        message.setFrom(request.senderPhoneNumber());
        message.setTo(request.recipientPhoneNumber());
        message.setText(request.title() + "\n" + request.content());
        return message;
    }
}
