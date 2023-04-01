package com.rudkids.rudkids.common.fixtures.user;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.user.application.UserService;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class UserServiceFixtures {

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    protected User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("namsewon@gmail.com")
                .name("남세")
                .age(null)
                .gender(null)
                .socialType(SocialType.GOOGLE)
                .build();

        userRepository.save(user);
    }
}
