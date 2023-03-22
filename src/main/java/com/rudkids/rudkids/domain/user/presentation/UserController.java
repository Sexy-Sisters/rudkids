package com.rudkids.rudkids.domain.user.presentation;

import com.rudkids.rudkids.domain.auth.dto.LoginUser;
import com.rudkids.rudkids.domain.auth.presentation.AuthenticationPrincipal;
import com.rudkids.rudkids.domain.user.application.UserService;
import com.rudkids.rudkids.domain.user.dto.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/update")
    public void update(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestBody UserUpdateRequest request) {
        userService.update(loginUser.getId(), request);
    }
}
