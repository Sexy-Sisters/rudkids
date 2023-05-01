package com.rudkids.rudkids.interfaces.user.dto;

import com.rudkids.rudkids.domain.user.UserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserCommand.Update toCommand(UserRequest.Update request) {
        return UserCommand.Update.builder()
            .name(request.name())
            .phoneNumber(request.phoneNumber())
            .profileImagePath(request.profileImagePath())
            .profileImageUrl(request.profileImageUrl())
            .build();
    }
}
