package com.rudkids.rudkids.interfaces.auth;

import com.rudkids.rudkids.domain.auth.exception.EmptyAuthorizationHeaderException;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class AuthorizationExtractor {
    private static final String BEARER_TYPE = "Bearer ";

    public static String extract(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new EmptyAuthorizationHeaderException();
        }

        validateAuthorizationFormat(authorizationHeader);
        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

    private static void validateAuthorizationFormat(String authorizationHeader) {
        if (!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
            throw new InvalidTokenException();
        }
    }
}
