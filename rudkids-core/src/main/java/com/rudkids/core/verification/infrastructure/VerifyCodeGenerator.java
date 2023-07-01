package com.rudkids.core.verification.infrastructure;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VerifyCodeGenerator {

    public String generate() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int code = random.nextInt(1000000) % 1000000;
        return String.valueOf(code);
    }
}