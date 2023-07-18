package com.rudkids.api.admin;

import com.rudkids.api.auth.AuthorizationExtractor;
import com.rudkids.core.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationAdminAuthorityInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            if(handlerMethod.hasMethodAnnotation(AuthenticationAdminAuthority.class)) {
                String accessToken = AuthorizationExtractor.extract(request);
                UUID userId = authService.extractUserId(accessToken);
                authService.validateAdminAuthority(userId);
                return true;
            }
        }

        return true;
    }
}
