package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.domain.user.UserCommand;

import java.util.UUID;

public interface UserService {
    void update(UUID id, UserCommand.Update request);
}
