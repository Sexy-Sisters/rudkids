package com.rudkids.core.user.domain;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User getUser(UUID id);
    User getUser(AuthUser.OAuth oauthUser);
    boolean existsUser(UUID id);
    List<User> getUsers(String email);
}