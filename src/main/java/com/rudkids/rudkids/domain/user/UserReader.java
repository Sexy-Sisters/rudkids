package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserReader {
    User getUser(UUID id);
    List<User> getUsers(String email);
}