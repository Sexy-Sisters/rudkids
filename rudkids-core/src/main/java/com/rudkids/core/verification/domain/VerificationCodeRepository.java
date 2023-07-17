package com.rudkids.core.verification.domain;

public interface VerificationCodeRepository {
    void save(String phoneNumber, String code);
    String get(String phoneNumber);
}