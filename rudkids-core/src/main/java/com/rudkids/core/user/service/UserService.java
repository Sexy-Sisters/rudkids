package com.rudkids.core.user.service;

import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void update(UUID userId, UserRequest.Update request);
    UserResponse.Info get(UUID userId);
    List<UserResponse.Address> getAddresses(UUID userId);
}
