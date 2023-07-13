package com.rudkids.core.common.fixtures.community;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.community.domain.CommunityRepository;
import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.infrastructure.JpaCommunityRepository;
import com.rudkids.core.community.service.CommunityService;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class CommunityServiceFixtures {

    @Autowired
    protected CommunityService communityService;

    @Autowired
    protected CommunityRepository communityRepository;

    @Autowired
    protected JpaCommunityRepository jpaCommunityRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JpaUserRepository jpaUserRepository;

    protected User user;

    protected CommunityRequest.Create COMMUNITY_작성_요청 = CommunityRequest.Create.builder()
        .title("제목")
        .content("내용")
        .type("POST")
        .image(new ImageRequest.Create("path", "url"))
        .build();

    protected CommunityRequest.Update COMMUNITY_수정_요청 = CommunityRequest.Update.builder()
        .title("새로운 제목")
        .content("새로운 내용")
        .image(new ImageRequest.Create("path", "url"))
        .build();

    @BeforeEach
    void setUp() {
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        jpaUserRepository.save(user);
    }
}
