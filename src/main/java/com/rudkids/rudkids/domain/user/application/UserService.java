package com.rudkids.rudkids.domain.user.application;

import com.rudkids.rudkids.domain.user.dto.request.SignUpRequest;

import java.util.UUID;

public interface UserService {
    void signUp(UUID id, SignUpRequest request);
}
