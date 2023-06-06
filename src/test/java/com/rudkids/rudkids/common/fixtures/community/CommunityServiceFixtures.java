package com.rudkids.rudkids.common.fixtures.community;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.service.CommunityService;
import com.rudkids.rudkids.domain.user.domain.*;
import com.rudkids.rudkids.infrastructure.community.CommunityRepository;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class CommunityServiceFixtures {

    @Autowired
    protected CommunityService communityService;

    @Autowired
    protected CommunityRepository communityRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User user;

    protected CommunityCommand.Create COMMUNITY_작성_요청 = CommunityCommand.Create.builder()
        .title("제목")
        .content("내용")
        .type("POST")
        .image(new ImageRequest("path", "url"))
        .build();

    protected CommunityCommand.Update COMMUNITY_수정_요청 = CommunityCommand.Update.builder()
        .title("새로운 제목")
        .content("새로운 내용")
        .image(new ImageRequest("path", "url"))
        .build();

    @BeforeEach
    void setUp() {
        var profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .age(18)
            .gender("MALE")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        userRepository.save(user);
    }
}
