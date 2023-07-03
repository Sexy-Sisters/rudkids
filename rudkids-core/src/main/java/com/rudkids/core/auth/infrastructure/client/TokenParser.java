package com.rudkids.core.auth.infrastructure.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.core.auth.exception.OAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TokenParser {
    private static final String JWT_DELIMITER = "\\.";
    private final ObjectMapper objectMapper;

    public <T> T parse(String token, Class<T> valueType) {
        String payload = getPayload(token);
        String decodedPayload = decodeJwtPayload(payload);

        try {
            return objectMapper.readValue(decodedPayload, valueType);
        } catch (final JsonProcessingException e) {
            throw new OAuthException();
        }
    }

    private String getPayload(String jwt) {
        return jwt.split(JWT_DELIMITER)[1];
    }

    private String decodeJwtPayload(String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
