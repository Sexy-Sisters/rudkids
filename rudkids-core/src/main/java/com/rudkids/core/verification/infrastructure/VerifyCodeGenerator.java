package com.rudkids.core.verification.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class VerifyCodeGenerator {
    private static final int CODE_LENGTH = 6;
    private static final StringBuilder sb = new StringBuilder(CODE_LENGTH);

    public String generate() {
        sb.setLength(0);
        String code = generateUniqueNumber();
        log.info("verify code, expire time is 3m = {}", code);
        return code;
    }

    private String generateUniqueNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}