package com.rudkids.core.auth.infrastructure;

import com.rudkids.core.auth.domain.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InMemoryAuthTokenRepository implements TokenRepository {
    private final Map<UUID, String> tokenRepository;

    @Override
    public String save(UUID userId, String refreshToken) {
        tokenRepository.put(userId, refreshToken);
        return tokenRepository.get(userId);
    }

    @Override
    public Optional<String> findByUserId(UUID userId) {
        return Optional.ofNullable(tokenRepository.get(userId));
    }
}
