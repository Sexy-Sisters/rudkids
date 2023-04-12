package com.rudkids.rudkids.interfaces.auth.dto;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthDtoMapper {

    public AuthCommand.RenewalAccessToken to(AuthRequest.RenewalToken request) {
        return AuthCommand.RenewalAccessToken.builder()
                .refreshToken(request.refreshToken())
                .build();

    }

    public AuthCommand.OAuthUser to(AuthUser.OAuth oAuthUser) {
        return AuthCommand.OAuthUser.builder()
                .email(oAuthUser.email())
                .name(oAuthUser.name())
                .build();
    }
}
