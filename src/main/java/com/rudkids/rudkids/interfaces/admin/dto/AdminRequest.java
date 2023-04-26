package com.rudkids.rudkids.interfaces.admin.dto;

import com.rudkids.rudkids.domain.user.domain.RoleType;

public class AdminRequest {

    public record ChangeUserRole(RoleType role) {
    }
}
