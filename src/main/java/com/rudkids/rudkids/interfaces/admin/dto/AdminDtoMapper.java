package com.rudkids.rudkids.interfaces.admin.dto;

import com.rudkids.rudkids.domain.admin.AdminCommand;
import org.springframework.stereotype.Component;

@Component
public class AdminDtoMapper {

    public AdminCommand.ChangeUserRole toCommand(AdminRequest.ChangeUserRole request) {
        return AdminCommand.ChangeUserRole.builder()
            .role(request.role())
            .build();
    }
}
