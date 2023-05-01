package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.domain.user.service.UserService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.user.dto.UserDtoMapper;
import com.rudkids.rudkids.interfaces.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PutMapping
    public void update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody UserRequest.Update request
        ) {
        var command = userDtoMapper.toCommand(request);
        userService.update(loginUser.id(), command);
    }
}
