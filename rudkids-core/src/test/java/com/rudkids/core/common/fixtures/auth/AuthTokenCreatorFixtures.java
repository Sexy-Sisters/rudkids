package com.rudkids.core.common.fixtures.auth;

import com.rudkids.core.auth.domain.TokenRepository;
import com.rudkids.core.auth.service.TokenCreator;
import com.rudkids.core.common.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class AuthTokenCreatorFixtures {

    @Autowired
    protected TokenCreator tokenCreator;

    @Autowired
    protected TokenRepository tokenRepository;
}
