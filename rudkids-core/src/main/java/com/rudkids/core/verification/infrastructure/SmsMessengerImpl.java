package com.rudkids.core.verification.infrastructure;

import com.rudkids.core.verification.exception.MessageSendFailException;
import com.rudkids.core.verification.service.SmsMessenger;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsMessengerImpl implements SmsMessenger {
    private final DefaultMessageService messageService;

    @Value("${sms.info.sender.phone-number}")
    private String senderPhoneNumber;

    @Override
    public void send(String phoneNumber, String verifyCode) {
        var request = createSendRequest(phoneNumber, verifyCode);
        sendVerifyCode(request);
    }

    private SingleMessageSendingRequest createSendRequest(String phoneNumber, String verifyCode) {
        Message message = new Message();
        message.setFrom(senderPhoneNumber);
        message.setTo(phoneNumber);
        message.setText(verifyCode);
        return new SingleMessageSendingRequest(message);
    }

    private void sendVerifyCode(SingleMessageSendingRequest request) {
        try {
            messageService.sendOne(request);
        } catch (Exception e) {
            throw new MessageSendFailException();
        }
    }
}
