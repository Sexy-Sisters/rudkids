package com.rudkids.core.verification.infrastructure;

import com.rudkids.core.verification.domain.VerificationCodeRepository;
import com.rudkids.core.verification.exception.VerificationCodeNotFoundException;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class InMemoryVerificationCodeRepository implements VerificationCodeRepository {

    private static final Map<String, String> CODE_REPOSITORY = ExpiringMap.builder()
        .maxSize(1000)
        .expirationPolicy(ExpirationPolicy.CREATED)
        .expiration(60, TimeUnit.SECONDS)
        .build();

    @Override
    public void save(String phoneNumber, String code) {
        CODE_REPOSITORY.put(phoneNumber, code);
    }

    @Override
    public String get(String code) {
        return Optional.ofNullable(CODE_REPOSITORY.get(code))
            .orElseThrow(VerificationCodeNotFoundException::new);
    }
}
