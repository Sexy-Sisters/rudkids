package com.rudkids.core.common.fixtures;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.service.AuthService;
import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class AuthServiceFixtures {

    @Autowired
    protected AuthService authService;

    @Autowired
    protected JpaUserRepository userRepository;

    protected AuthUser.OAuth oAuthUser = AuthUser.OAuth.builder()
        .email("namse@gmail.com")
        .name("남세")
        .picture("http://")
        .build();
}
