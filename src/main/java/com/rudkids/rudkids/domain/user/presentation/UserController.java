package com.rudkids.rudkids.domain.user.presentation;

import com.rudkids.rudkids.domain.auth.dto.LoginUser;
import com.rudkids.rudkids.domain.auth.presentation.AuthenticationPrincipal;
import com.rudkids.rudkids.domain.user.application.UserService;
import com.rudkids.rudkids.domain.user.application.dto.request.UserUpdateServiceDto;
import com.rudkids.rudkids.domain.user.presentation.dto.UserDtoMapper;
import com.rudkids.rudkids.domain.user.presentation.dto.request.SignUpRequest;
import com.rudkids.rudkids.domain.user.presentation.dto.request.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/signUp")
    public void signUp(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestBody SignUpRequest request) {
        UserUpdateServiceDto serviceRequestDto = userDtoMapper.of(request);
        userService.update(loginUser.getId(), serviceRequestDto);
    }

    @PutMapping("/update")
    public void update(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestBody UserUpdateDto request) {
        UserUpdateServiceDto serviceRequestDto = userDtoMapper.of(request);
        userService.update(loginUser.getId(), serviceRequestDto);
    }
}
