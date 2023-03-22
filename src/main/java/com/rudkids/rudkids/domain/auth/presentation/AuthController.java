package com.rudkids.rudkids.domain.auth.presentation;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.domain.auth.dto.request.TokenRequest;
import com.rudkids.rudkids.domain.auth.dto.response.OAuthUriResponse;
import com.rudkids.rudkids.domain.auth.application.AuthServiceImpl;
import com.rudkids.rudkids.domain.auth.application.OAuthClient;
import com.rudkids.rudkids.domain.auth.application.OAuthUri;
import com.rudkids.rudkids.domain.auth.dto.LoginUser;
import com.rudkids.rudkids.domain.auth.dto.request.TokenRenewalRequest;
import com.rudkids.rudkids.domain.auth.dto.response.AccessAndRefreshTokenResponse;
import com.rudkids.rudkids.domain.auth.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OAuthUri oAuthUri;
    private final AuthServiceImpl authServiceImpl;
    private final OAuthClient oAuthClient;

    @GetMapping("/{oauthProvider}/oauth-uri")
    public OAuthUriResponse generateLink(@PathVariable final String oauthProvider,
                                         @RequestParam final String redirectUri) {
        return new OAuthUriResponse(oAuthUri.generate(redirectUri));
    }

    @PostMapping("/{oauthProvider}/token")
    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(
            @PathVariable final String oauthProvider, @RequestBody TokenRequest tokenRequest
    ) {
        OAuthUser oAuthUser = oAuthClient.getOAuthUser(tokenRequest.getAuthorizationCode(), tokenRequest.getRedirectUri());
        return authServiceImpl.generateAccessAndRefreshToken(oAuthUser);
    }

    @PostMapping("/renewal/access")
    public AccessTokenResponse generateRenewalAccessToken(@RequestBody TokenRenewalRequest tokenRenewalRequest) {
        return authServiceImpl.generateRenewalAccessToken(tokenRenewalRequest);
    }

    @GetMapping("/validate/token")
    public void validateToken(@AuthenticationPrincipal LoginUser loginUser) {
    }
}
