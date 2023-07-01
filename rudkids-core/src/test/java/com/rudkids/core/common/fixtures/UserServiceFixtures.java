package com.rudkids.core.common.fixtures;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import com.rudkids.core.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class UserServiceFixtures {

    @Autowired
    protected UserService userService;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User user;

    @BeforeEach
    void inputData() {
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세원"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        jpaUserRepository.save(user);
    }
}
