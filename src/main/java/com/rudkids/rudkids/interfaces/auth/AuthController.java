package com.rudkids.rudkids.interfaces.auth;

import com.rudkids.rudkids.domain.auth.application.AuthCommand;
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
    public ResponseEntity<AuthResponse.OAuthUri> generateLink(
            @PathVariable final String oauthProvider,
            @RequestParam final String redirectUri
    ) {
        AuthResponse.OAuthUri response = new AuthResponse.OAuthUri(oAuthUri.generate(redirectUri));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{oauthProvider}/token")
    public ResponseEntity<AuthResponse.AccessAndRefreshToken> generateAccessAndRefreshToken(
            @PathVariable final String oauthProvider,
            @RequestBody AuthRequest.Token tokenRequest
    ) {
        AuthUser.OAuth oAuthUser = oAuthClient.getOAuthUser(tokenRequest.authorizationCode(), tokenRequest.redirectUri());
        AuthCommand.OAuthUser serviceRequestDto = authDtoMapper.toServiceDto(oAuthUser);
        AuthResponse.AccessAndRefreshToken response = authService.generateAccessAndRefreshToken(serviceRequestDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/renewal/access")
    public ResponseEntity<AuthResponse.AccessToken> generateRenewalAccessToken(@RequestBody AuthRequest.RenewalToken tokenRenewalRequest) {
        AuthCommand.RenewalAccessToken serviceRequestDto = authDtoMapper.toServiceDto(tokenRenewalRequest);
        AuthResponse.AccessToken response = authService.generateRenewalAccessToken(serviceRequestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal AuthUser.Login loginUser) {
    }
}
