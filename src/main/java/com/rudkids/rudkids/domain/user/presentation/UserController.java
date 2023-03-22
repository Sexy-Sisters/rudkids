package com.rudkids.rudkids.domain.user.presentation;

import com.rudkids.rudkids.domain.auth.dto.LoginUser;
import com.rudkids.rudkids.domain.auth.presentation.AuthenticationPrincipal;
import com.rudkids.rudkids.domain.user.application.UserService;
import com.rudkids.rudkids.domain.user.dto.request.SignUpRequest;
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

    @PostMapping("/signUp")
    public void signUp(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestBody SignUpRequest request) {
        userService.signUp(loginUser.getId(), request);
    }
}
