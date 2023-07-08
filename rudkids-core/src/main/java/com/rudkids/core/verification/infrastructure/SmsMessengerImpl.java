package com.rudkids.core.verification.infrastructure;

import com.rudkids.core.verification.exception.MessageSendFailException;
import com.rudkids.core.verification.service.SmsMessenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsMessengerImpl implements SmsMessenger {
    private final DefaultMessageService messageService;

    @Value("${sms.info.sender.phone-number}")
    private String senderPhoneNumber;

    @Override
    public void send(String phoneNumber, String content) {
        var request = createSendRequest(phoneNumber, content);
        sendMessage(request);
    }

    private SingleMessageSendingRequest createSendRequest(String phoneNumber, String content) {
        Message message = new Message();
        message.setFrom(senderPhoneNumber);
        message.setTo(phoneNumber);
        message.setText(content);
        return new SingleMessageSendingRequest(message);
    }

    private void sendMessage(SingleMessageSendingRequest request) {
        try {
            messageService.sendOne(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MessageSendFailException();
        }
    }
}
