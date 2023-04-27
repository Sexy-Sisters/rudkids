package com.rudkids.rudkids.domain.admin;

import com.rudkids.rudkids.domain.user.domain.RoleType;
import lombok.Builder;

public class AdminCommand {

    @Builder
    public record ChangeUserRole(RoleType role) {
    }
}
