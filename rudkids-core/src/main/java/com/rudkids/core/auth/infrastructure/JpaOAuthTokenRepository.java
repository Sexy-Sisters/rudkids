package com.rudkids.core.auth.infrastructure;

import com.rudkids.core.auth.domain.OAuthToken;
import com.rudkids.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaOAuthTokenRepository extends JpaRepository<OAuthToken, UUID> {
    Optional<OAuthToken> findByUser(User user);
}
