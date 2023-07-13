package com.rudkids.core.common.fixtures.communityLike;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.domain.CommunityImage;
import com.rudkids.core.community.domain.Content;
import com.rudkids.core.community.domain.Title;
import com.rudkids.core.community.infrastructure.JpaCommunityRepository;
import com.rudkids.core.communityLike.infrastructure.JpaCommunityLikeRepository;
import com.rudkids.core.communityLike.service.CommunityLikeService;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class CommunityLikeServiceFixtures {

    @Autowired
    protected CommunityLikeService communityLikeService;

    @Autowired
    protected JpaCommunityRepository communityRepository;

    @Autowired
    protected JpaCommunityLikeRepository communityLikeRepository;

    @Autowired
    protected JpaUserRepository userRepository;

    protected User user;
    protected Community community;

    @BeforeEach
    void setUp() {
        ProfileImage profileImage = ProfileImage.create("path", "url");
        user = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .profileImage(profileImage)
            .build();
        userRepository.save(user);

        community = Community.create(
            user,
            Title.create("title"),
            Content.create("content"),
            CommunityImage.create("path", "url")
        );
        communityRepository.save(community);
    }
}
