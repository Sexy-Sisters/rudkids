package com.rudkids.rudkids.domain.admin.service;

import com.rudkids.rudkids.domain.admin.AdminCommand;
import com.rudkids.rudkids.domain.admin.AdminInfo;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<AdminInfo.User> searchUser(String email);
    void changeUserRole(UUID userId, AdminCommand.ChangeUserRole command);
}
