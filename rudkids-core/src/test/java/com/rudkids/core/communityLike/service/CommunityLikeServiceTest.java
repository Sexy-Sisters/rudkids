package com.rudkids.core.communityLike.service;

import com.rudkids.core.common.fixtures.communityLike.CommunityLikeServiceFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommunityLikeServiceTest extends CommunityLikeServiceFixtures {

    @Nested
    @DisplayName("좋아요가 실행된다")
    class like {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            communityLikeService.like(user.getId(), community.getId());

            //then
            assertThat(community.getLikeCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("성공: 싫어요")
        void success2() {
            //given
            communityLikeService.like(user.getId(), community.getId());

            //when
            communityLikeService.like(user.getId(), community.getId());

            //then
            boolean hasCommunityLike = communityLikeRepository.existsByCommunityAndUser(community, user);
            assertThat(hasCommunityLike).isFalse();
        }
    }
}
