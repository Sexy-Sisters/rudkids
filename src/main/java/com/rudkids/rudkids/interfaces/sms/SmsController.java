package com.rudkids.rudkids.interfaces.sms;

import com.rudkids.rudkids.interfaces.sms.dto.SmsRequest;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {
    private final DefaultMessageService messageService;

    @PostMapping
    public void send(@RequestBody SmsRequest.Send request) {
        messageService.sendOne(new SingleMessageSendingRequest(request.toMessage()));
    }
}