package com.rudkids.rudkids.domain.user.application;

import com.rudkids.rudkids.domain.user.dto.request.UserUpdateRequest;

import java.util.UUID;

public interface UserService {
    void update(UUID id, UserUpdateRequest request);
}
