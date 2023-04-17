package com.rudkids.rudkids.infrastructure.oauth;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository {
    String save(UUID userId, String refreshToken);
    Optional<String> findByUserId(UUID userId);
}
