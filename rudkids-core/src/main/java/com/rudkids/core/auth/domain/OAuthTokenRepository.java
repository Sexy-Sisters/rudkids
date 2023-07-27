package com.rudkids.core.auth.domain;

import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.user.domain.User;

public interface OAuthTokenRepository {
    OAuthToken get(User user);
    OAuthToken getOrCreate(User user, AuthUser.OAuth oAuthUser);
}