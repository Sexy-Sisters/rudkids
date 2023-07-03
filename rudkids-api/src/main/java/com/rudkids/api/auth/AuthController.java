package com.rudkids.api.auth;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.service.AuthService;
import com.rudkids.core.auth.service.OAuthClient;
import com.rudkids.core.auth.service.OAuthUri;
import jakarta.servlet.http.HttpServletRequest;
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
        var response = authService.generateAccessAndRefreshToken(oAuthUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/renewal/access")
    public ResponseEntity generateRenewalAccessToken(@RequestBody AuthRequest.RenewalToken request) {
        var response = authService.generateRenewalAccessToken(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/register")
    public ResponseEntity<Void> registerInformation(
        HttpServletRequest httpRequest,
        @RequestBody AuthRequest.Register request
    ) {
        var accessToken = AuthorizationExtractor.extract(httpRequest);
        var userId = authService.extractUserId(accessToken);
        authService.registerInformation(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal AuthUser.Login loginUser) {
    }
}
