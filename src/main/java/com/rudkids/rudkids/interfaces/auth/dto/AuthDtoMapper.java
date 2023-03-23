package com.rudkids.rudkids.interfaces.auth.dto;

import com.rudkids.rudkids.domain.auth.application.AuthCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthDtoMapper {

    public AuthCommand.RenewalToken of(AuthRequest.RenewalToken request) {
        return new AuthCommand.RenewalToken(request.getRefreshToken());
    }

    public AuthCommand.OAuthUser of(AuthUser.OAuth oAuthUser) {
        return new AuthCommand.OAuthUser(oAuthUser.getEmail(), oAuthUser.getName());
    }
}
