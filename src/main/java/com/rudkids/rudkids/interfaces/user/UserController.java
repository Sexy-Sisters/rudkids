package com.rudkids.rudkids.interfaces.user;

import com.rudkids.rudkids.domain.user.service.UserService;
import com.rudkids.rudkids.interfaces.auth.AuthenticationPrincipal;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import com.rudkids.rudkids.interfaces.user.dto.UserDtoMapper;
import com.rudkids.rudkids.interfaces.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PatchMapping
    public void signUp(@AuthenticationPrincipal AuthUser.Login loginUser, @RequestBody UserRequest.SignUp request) {
        var command = userDtoMapper.toCommand(request);
        userService.signUp(loginUser.id(), command);
    }

    @PutMapping
    public void update(@AuthenticationPrincipal AuthUser.Login loginUser, @RequestBody UserRequest.Update request) {
        var command = userDtoMapper.toCommand(request);
        userService.update(loginUser.id(), command);
    }

    @GetMapping
    public ResponseEntity find(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = userService.find(loginUser.id());
        return ResponseEntity.ok(response);
    }
}