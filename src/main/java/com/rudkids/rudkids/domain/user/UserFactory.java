package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.user.domain.User;

public interface UserFactory {
    User create(AuthCommand.OAuthUser oAuthUser);
    void update(User user, UserCommand.Update command);
}
