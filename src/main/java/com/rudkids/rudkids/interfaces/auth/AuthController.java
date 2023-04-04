package com.rudkids.rudkids.interfaces.auth;

import com.rudkids.rudkids.domain.auth.application.AuthService;
import com.rudkids.rudkids.domain.auth.application.OAuthClient;
import com.rudkids.rudkids.domain.auth.application.OAuthUri;
import com.rudkids.rudkids.interfaces.auth.dto.AuthDtoMapper;
import com.rudkids.rudkids.interfaces.auth.dto.AuthRequest;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OAuthUri oAuthUri;
    private final AuthService authService;
    private final OAuthClient oAuthClient;
    private final AuthDtoMapper authDtoMapper;

    @GetMapping("/{oauthProvider}/oauth-uri")
    public ResponseEntity generateLink(
            @PathVariable final String oauthProvider,
            @RequestParam final String redirectUri
    ) {
        var response = new AuthResponse.OAuthUri(oAuthUri.generate(redirectUri));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{oauthProvider}/token")
    public ResponseEntity generateAccessAndRefreshToken(
            @PathVariable final String oauthProvider,
            @RequestBody AuthRequest.Token request
    ) {
        var oAuthUser = oAuthClient.getOAuthUser(request.authorizationCode(), request.redirectUri());
        var command = authDtoMapper.of(oAuthUser);
        var response = authService.generateAccessAndRefreshToken(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/renewal/access")
    public ResponseEntity generateRenewalAccessToken(@RequestBody AuthRequest.RenewalToken request) {
        var command = authDtoMapper.of(request);
        var response = authService.generateRenewalAccessToken(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal AuthUser.Login loginUser) {
    }
}
