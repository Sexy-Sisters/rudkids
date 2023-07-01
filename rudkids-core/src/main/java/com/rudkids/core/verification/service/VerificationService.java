package com.rudkids.core.verification.service;

import com.rudkids.core.verification.dto.VerifyRequest;

public interface VerificationService {

    void send(VerifyRequest.Send request);

    void check(VerifyRequest.Check request);
}
