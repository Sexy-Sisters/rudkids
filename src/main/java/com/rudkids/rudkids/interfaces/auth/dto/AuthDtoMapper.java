package com.rudkids.rudkids.interfaces.auth.dto;

import com.rudkids.rudkids.domain.auth.application.AuthCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthDtoMapper {

    public AuthCommand.RenewalToken of(AuthRequest.RenewalToken request) {
        return AuthCommand.RenewalToken.builder()
                .refreshToken(request.refreshToken())
                .build();
    }

    public AuthCommand.OAuthUser of(AuthUser.OAuth oAuthUser) {
        return AuthCommand.OAuthUser.builder()
                .email(oAuthUser.email())
                .name(oAuthUser.name())
                .build();
    }
}
