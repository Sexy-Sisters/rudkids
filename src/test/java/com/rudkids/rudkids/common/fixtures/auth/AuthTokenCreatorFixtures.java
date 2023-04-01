package com.rudkids.rudkids.common.fixtures.auth;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.auth.application.TokenCreator;
import com.rudkids.rudkids.domain.auth.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class AuthTokenCreatorFixtures {

    @Autowired
    protected TokenCreator tokenCreator;

    @Autowired
    protected TokenRepository tokenRepository;
}
