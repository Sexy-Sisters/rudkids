package com.rudkids.core.verification.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class VerifyCodeGenerator {
    private static final int CODE_LENGTH = 1000000;

    public String generate() {
        String code = generateUniqueNumber();
        log.info("verify code, expire time is 3m = {}", code);
        return code;
    }

    private String generateUniqueNumber() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int code = random.nextInt(CODE_LENGTH) % CODE_LENGTH;
        return String.valueOf(code);
    }
}