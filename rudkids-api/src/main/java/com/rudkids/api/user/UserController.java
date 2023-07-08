package com.rudkids.api.user;

import com.rudkids.api.auth.AuthenticationPrincipal;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<Void> update(
        @AuthenticationPrincipal AuthUser.Login loginUser,
        @RequestBody UserRequest.Update request) {
        userService.update(loginUser.id(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity get(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = userService.get(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/community")
    public ResponseEntity getMyCommunities(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = userService.getMyCommunities(loginUser.id());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/address")
    public ResponseEntity getAddresses(@AuthenticationPrincipal AuthUser.Login loginUser) {
        var response = userService.getAddresses(loginUser.id());
        return ResponseEntity.ok(response);
    }
}
