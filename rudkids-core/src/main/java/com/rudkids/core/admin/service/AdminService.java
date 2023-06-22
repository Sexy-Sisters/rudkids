package com.rudkids.core.admin.service;

import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<AdminResponse.UserInfo> searchUser(String email);
    void changeUserRole(UUID userId, AdminRequest.ChangeUserRole request);
}
