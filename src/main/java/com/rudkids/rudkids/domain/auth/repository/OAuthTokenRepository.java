package com.rudkids.rudkids.domain.auth.repository;

import com.rudkids.rudkids.domain.auth.domain.OAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, UUID> {
}
