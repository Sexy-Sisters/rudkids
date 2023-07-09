package com.rudkids.api.auth;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.auth.service.AuthService;
import com.rudkids.core.auth.service.OAuthClient;
import com.rudkids.core.auth.service.OAuthUri;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OAuthUri oAuthUri;
    private final AuthService authService;
    private final OAuthClient oAuthClient;

    @GetMapping("/{oauthProvider}/oauth-uri")
    public ResponseEntity generateLink(
        @PathVariable final String oauthProvider,
        @RequestParam final String redirectUri
    ) {
        var response = oAuthUri.generate(oauthProvider, redirectUri);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{oauthProvider}/token")
    public ResponseEntity generateAccessAndRefreshToken(
        @PathVariable final String oauthProvider,
        @RequestBody AuthRequest.Token request
    ) {
        var oAuthUser = oAuthClient.getOAuthUser(oauthProvider, request.authorizationCode(), request.redirectUri());
        var tokens = authService.generateAccessAndRefreshToken(oAuthUser);
        var hasPhoneNumber = authService.getHasPhoneNumber(oAuthUser.email());
        var authResponse = new AuthResponse.Login(tokens, hasPhoneNumber);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/renewal/access")
    public ResponseEntity generateRenewalAccessToken(@RequestBody AuthRequest.RenewalToken request) {
        var response = authService.generateRenewalAccessToken(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal AuthUser.Login loginUser) {
    }
}
