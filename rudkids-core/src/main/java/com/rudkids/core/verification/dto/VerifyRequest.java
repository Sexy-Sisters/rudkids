package com.rudkids.core.verification.dto;

public class VerifyRequest {

    public record Send(String phoneNumber) {}

    public record Check(String code) {}
}
