package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserReader {
    User getUser(UUID userId);
    User getUser(AuthCommand.OAuthUser oAuthUser);
    boolean existsUser(UUID userId);
    List<User> getUsers(String email);
}