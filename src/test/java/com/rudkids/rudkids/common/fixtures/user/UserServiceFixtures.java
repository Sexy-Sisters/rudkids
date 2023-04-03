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

    protected static final int 유저_나이 = 18;
    protected static final String 유저_성별 = "MALE";
    protected static final int 잘못된_유저_나이 = 103;
    protected static final String 잘못된_유저_성별 = "Male";

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    protected User user = User.builder()
            .email("namsewon@gmail.com")
            .name("남세")
            .age(null)
            .gender(null)
            .socialType(SocialType.GOOGLE)
            .build();

    @BeforeEach
    void setUp() {
        userRepository.save(user);
    }
}
