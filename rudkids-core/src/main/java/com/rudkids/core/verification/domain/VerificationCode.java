package com.rudkids.core.verification.domain;

import com.rudkids.core.verification.exception.InvalidVerificationCodeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerificationCode {
    private String code;

    private VerificationCode(String code) {
        this.code = code;
    }

    public static VerificationCode create(String code) {
        return new VerificationCode(code);
    }

    public void validateHasSameCode(String code) {
        if(!this.code.equals(code)) {
            throw new InvalidVerificationCodeException();
        }
    }
}
