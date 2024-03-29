package com.rudkids.core.common.fixtures.auth;

import com.rudkids.core.auth.service.JwtTokenProvider;
import com.rudkids.core.common.ServiceTest;

@ServiceTest
public class JwtTokenProviderFixtures {

    protected static final String JWT_SECRET_KEY = "a".repeat(32); // Secret Key는 최소 32바이트 이상이어야함.
    protected static final int ACCESS_TOKEN_EXPIRE_TIME = 3600;
    protected static final int REFRESH_TOKEN_EXPIRE_TIME = 3600;
    protected static final String PAYLOAD = "payload";

    protected final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(
            JWT_SECRET_KEY,
            ACCESS_TOKEN_EXPIRE_TIME,
            REFRESH_TOKEN_EXPIRE_TIME
    );
}
