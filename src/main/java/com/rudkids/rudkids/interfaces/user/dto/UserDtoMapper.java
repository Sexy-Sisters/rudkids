package com.rudkids.rudkids.interfaces.user.dto;

import com.rudkids.rudkids.domain.user.application.UserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserCommand.Update of(UserRequest.SignUp request) {
        return UserCommand.Update.builder()
                .age(request.age())
                .gender(request.gender())
                .build();
    }

    public UserCommand.Update of(UserRequest.Update request) {
        return UserCommand.Update.builder()
                .age(request.age())
                .gender(request.gender())
                .build();
    }
}
