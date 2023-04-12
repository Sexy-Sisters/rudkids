package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.user.domain.User;

import java.util.UUID;

public interface UserReader {
    User getUser(UUID id);
}