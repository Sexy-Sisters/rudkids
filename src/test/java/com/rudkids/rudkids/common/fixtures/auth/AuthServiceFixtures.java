package com.rudkids.rudkids.common.fixtures.auth;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.auth.service.AuthService;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class AuthServiceFixtures {

    @Autowired
    protected AuthService authService;

    @Autowired
    protected UserRepository userRepository;

    protected AuthCommand.OAuthUser oAuthUser = AuthCommand.OAuthUser.builder()
            .email("namse@gmail.com")
            .name("남세")
            .build();
}
