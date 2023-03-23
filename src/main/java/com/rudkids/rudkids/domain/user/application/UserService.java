package com.rudkids.rudkids.domain.user.application;

import com.rudkids.rudkids.domain.user.application.dto.request.UserUpdateServiceDto;

import java.util.UUID;

public interface UserService {
    void update(UUID id, UserUpdateServiceDto request);
}
