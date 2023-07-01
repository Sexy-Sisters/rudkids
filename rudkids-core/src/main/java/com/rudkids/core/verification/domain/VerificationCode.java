package com.rudkids.core.verification.domain;

import com.rudkids.core.verification.exception.NoSuchVerificationCodeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationCode {
    private String code;

    public VerificationCode(String code) {
        this.code = code;
    }

    public void validateHasSameRefreshToken(String code) {
        if(!this.code.equals(code)) {
            throw new NoSuchVerificationCodeException();
        }
    }
}
