package com.rudkids.core.verification.service;

public interface SmsMessenger {
    void send(String phoneNumber, String content);
}
