package com.rudkids.core.verification.service;

import com.rudkids.core.verification.domain.VerificationCode;
import com.rudkids.core.verification.domain.VerificationCodeRepository;
import com.rudkids.core.verification.dto.VerifyRequest;
import com.rudkids.core.user.exception.PhoneNumberEmptyException;
import com.rudkids.core.verification.infrastructure.VerifyCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationServiceImpl implements VerificationService {
    private final SmsMessenger smsMessenger;
    private final VerifyCodeGenerator verifyCodeGenerator;
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public void send(VerifyRequest.Send request) {
        validate(request);
        String code = verifyCodeGenerator.generate();
        smsMessenger.send(request.phoneNumber(), code);
        verificationCodeRepository.save(code);
    }

    private void validate(VerifyRequest.Send request) {
        if(isEmptyPhoneNumber(request.phoneNumber())) {
            throw new PhoneNumberEmptyException();
        }
    }

    private boolean isEmptyPhoneNumber(String phoneNumber) {
        return Objects.requireNonNull(phoneNumber).trim().isEmpty();
    }

    @Override
    public void check(VerifyRequest.Check request) {
        String foundCode = verificationCodeRepository.get(request.code());
        var verificationCode = new VerificationCode(foundCode);
        verificationCode.validateHasSameRefreshToken(request.code());
    }
}
