package com.rudkids.core.verification.domain;

public interface VerificationCodeRepository {
    void save(String code);
    String get(String code);
}