package com.rudkids.rudkids.domain.auth.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAuthTokenRepository implements TokenRepository {
    private static final Map<UUID, String> TOKEN_REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public String save(UUID userId, String refreshToken) {
        TOKEN_REPOSITORY.put(userId, refreshToken);
        return TOKEN_REPOSITORY.get(userId);
    }

    @Override
    public Optional<String> findByUserId(UUID userId) {
        return Optional.ofNullable(TOKEN_REPOSITORY.get(userId));
    }


}
