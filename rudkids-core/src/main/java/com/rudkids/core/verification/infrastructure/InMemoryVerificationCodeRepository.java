package com.rudkids.core.verification.infrastructure;

import com.rudkids.core.verification.domain.VerificationCodeRepository;
import com.rudkids.core.verification.exception.ExpiredCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InMemoryVerificationCodeRepository implements VerificationCodeRepository {

    private final Map<String, String> codeRepository;

    @Override
    public void save(String phoneNumber, String code) {
        codeRepository.put(phoneNumber, code);
    }

    @Override
    public String get(String phoneNumber) {
        return Optional.ofNullable(codeRepository.get(phoneNumber))
            .orElseThrow(ExpiredCodeException::new);
    }
}
