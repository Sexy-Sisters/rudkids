package com.rudkids.rudkids.domain.user.application;

import java.util.UUID;

public interface UserService {
    void update(UUID id, UserCommand.Update request);
}
