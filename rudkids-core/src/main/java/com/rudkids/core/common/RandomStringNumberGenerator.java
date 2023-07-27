package com.rudkids.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class RandomStringNumberGenerator implements RandomGeneratable {

    @Override
    public String generate(int count) {
        String number = generateUniqueNumber(count);
        log.info("random number = {}", number);
        return number;
    }

    private String generateUniqueNumber(final int count) {
        StringBuilder sb = new StringBuilder(count);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
