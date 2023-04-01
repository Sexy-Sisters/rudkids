package com.rudkids.rudkids.common.fixtures.auth;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.auth.application.AuthCommand;
import com.rudkids.rudkids.domain.auth.application.AuthService;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class AuthServiceFixtures {

    @Autowired
    protected AuthService authService;

    @Autowired
    protected UserRepository userRepository;

    protected AuthCommand.OAuthUser oAuthUser;

    @BeforeEach
    void setUp() {
        oAuthUser = new AuthCommand.OAuthUser("namse@gmail.com", "남세");
    }
}
