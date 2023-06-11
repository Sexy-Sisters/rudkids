package com.rudkids.rudkids.common.fixtures.user;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.user.UserMapper;
import com.rudkids.rudkids.domain.user.UserReader;
import com.rudkids.rudkids.domain.user.domain.*;
import com.rudkids.rudkids.domain.user.service.UserService;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class UserServiceFixtures {

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected UserReader userReader;

    @Autowired
    protected UserRepository userRepository;

    protected User user;

    @BeforeEach
    void inputData() {
        ProfileImage profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .name(UserName.create("남세원"))
            .age(19)
            .email("namse@gmail.com")
            .gender("MALE")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        userRepository.save(user);
    }
}
