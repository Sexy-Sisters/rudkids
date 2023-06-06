package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.common.fixtures.community.CommunityServiceFixtures;
import com.rudkids.rudkids.domain.community.CommunityInfo;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.CommunityImage;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityServiceTest extends CommunityServiceFixtures {

    @DisplayName("[커뮤니티-생성]")
    @Test
    void 커뮤니티_글을_작성한다() {
        //given, when
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //then
        Community actual = communityRepository.findById(communityId)
            .orElseThrow(CommunityNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("제목");
            assertThat(actual.getContent()).isEqualTo("내용");
        });
    }

    @DisplayName("[커뮤니티-수정]")
    @Test
    void 커뮤니티_글을_수정한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when
        communityService.update(user.getId(), communityId, COMMUNITY_수정_요청);

        //then
        Community actual = communityRepository.findById(communityId)
            .orElseThrow(CommunityNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("새로운 제목");
            assertThat(actual.getContent()).isEqualTo("새로운 내용");
        });
    }

    @DisplayName("[커뮤니티글-삭제]")
    @Test
    void 커뮤니티_글을_삭제한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when
        communityService.delete(user.getId(), communityId);

        //then
        boolean actual = communityRepository.findById(communityId).isPresent();
        assertThat(actual).isFalse();
    }

    @DisplayName("[커뮤니티-전체조회]")
    @Test
    void 아무나_전체조회할_수_있다() {
        //given
        communityService.create(user.getId(), COMMUNITY_작성_요청);
        communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when
        int page = 0;
        int size = 4;
        Pageable pageable = PageRequest.of(page, size);
        List<CommunityInfo.Main> actual = communityService.findAll("POST", pageable);

        //then
        assertThat(actual).hasSize(2);
    }

    @DisplayName("[커뮤니티-상세조회]")
    @Test
    void 아무나_상세조회할_수_있다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when
        CommunityInfo.Detail actual = communityService.find(communityId);

        //then
        assertAll(() -> {
            assertThat(actual.title()).isEqualTo("제목");
            assertThat(actual.content()).isEqualTo("내용");
            assertThat(actual.writer()).isEqualTo("남세");
        });
    }
}