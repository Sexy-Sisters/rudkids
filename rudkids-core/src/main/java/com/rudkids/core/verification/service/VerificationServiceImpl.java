package com.rudkids.core.verification.service;

import com.rudkids.core.common.RandomGeneratable;
import com.rudkids.core.verification.domain.VerificationCode;
import com.rudkids.core.verification.domain.VerificationCodeRepository;
import com.rudkids.core.verification.dto.VerifyRequest;
import com.rudkids.core.user.exception.PhoneNumberEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private static final int VERIFY_CODE_LENGTH = 6;

    private final SmsMessenger smsMessenger;
    private final RandomGeneratable randomGeneratable;
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public void send(VerifyRequest.Send request) {
        validate(request);
        String code = randomGeneratable.generate(VERIFY_CODE_LENGTH);
        verificationCodeRepository.save(request.phoneNumber(), code);
        smsMessenger.send(request.phoneNumber(), code);
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
    public String check(VerifyRequest.Check request) {
        String foundCode = verificationCodeRepository.get(request.phoneNumber());
        var verificationCode = VerificationCode.create(foundCode);
        verificationCode.validateHasSameRefreshToken(request.code());
        return request.phoneNumber();
    }
}
