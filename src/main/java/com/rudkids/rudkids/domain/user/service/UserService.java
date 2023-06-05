package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.UserInfo;

import java.util.UUID;

public interface UserService {
    void update(UUID userId, UserCommand.Update command);
    UserInfo.Main find(UUID userId);
}
