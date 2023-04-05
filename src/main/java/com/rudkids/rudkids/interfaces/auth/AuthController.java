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
@RequestMapping("/api/v1/auth")
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
        @RequestBody AuthRequest.Token tokenRequest
    ) {
        var oAuthUser = oAuthClient.getOAuthUser(tokenRequest.authorizationCode(), tokenRequest.redirectUri());
        var command = authDtoMapper.to(oAuthUser);
        var response = authService.generateAccessAndRefreshToken(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/renewal/access")
    public ResponseEntity generateRenewalAccessToken(@RequestBody AuthRequest.RenewalToken tokenRenewalRequest) {
        var command = authDtoMapper.to(tokenRenewalRequest);
        var response = authService.generateRenewalAccessToken(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal AuthUser.Login loginUser) {
    }
}
