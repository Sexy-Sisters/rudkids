package com.rudkids.core.community.service;

import com.rudkids.core.common.fixtures.community.CommunityServiceFixtures;
import com.rudkids.core.community.domain.Community;
import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.dto.CommunityResponse;
import com.rudkids.core.community.exception.CommunityNotFoundException;
import com.rudkids.core.community.exception.CommunityTypeNotFoundException;
import com.rudkids.core.community.exception.InvalidCommunityContentException;
import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityServiceTest extends CommunityServiceFixtures {

    @Nested
    @DisplayName("커뮤니티를 생성한다")
    class create {

        @Test
        @DisplayName("성공")
        void success() {
            //given, when
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //then
            Community actual = communityRepository.getCommunity(communityId);

            assertAll(() -> {
                assertThat(actual.getTitle()).isEqualTo("제목");
                assertThat(actual.getContent()).isEqualTo("내용");
            });
        }

        @Test
        @DisplayName("실패: 잘못된 제목")
        void fail() {
            //given
            String invalidTitle = "a".repeat(51);
            CommunityRequest.Create command = CommunityRequest.Create.builder()
                .title(invalidTitle)
                .content("내용")
                .type("POST")
                .image(new ImageRequest.Create("path", "url"))
                .build();

            //when, then
            assertThatThrownBy(() -> communityService.create(user.getId(), command))
                .isInstanceOf(InvalidCommunityTitleException.class);
        }

        @DisplayName("실패: 잘못된 내용")
        @Test
        void fail2() {
            //given
            String invalidContent = " ";
            CommunityRequest.Create command = CommunityRequest.Create.builder()
                .title("제목")
                .content(invalidContent)
                .type("POST")
                .image(new ImageRequest.Create("path", "url"))
                .build();

            //when, then
            assertThatThrownBy(() -> communityService.create(user.getId(), command))
                .isInstanceOf(InvalidCommunityContentException.class);
        }

        @DisplayName("실패: 잘못된 타입")
        @Test
        void fail3() {
            //given
            User admin = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            admin.changeAuthorityAdmin();
            jpaUserRepository.save(admin);

            String invalidType = " ";
            CommunityRequest.Create command = CommunityRequest.Create.builder()
                .title("제목")
                .content("내용")
                .type(invalidType)
                .image(new ImageRequest.Create("path", "url"))
                .build();

            //when, then
            assertThatThrownBy(() -> communityService.create(admin.getId(), command))
                .isInstanceOf(CommunityTypeNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("커뮤니티 리스트를 조회한다")
    class getAll {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            communityService.create(user.getId(), COMMUNITY_작성_요청);
            communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when
            int page = 0;
            int size = 4;
            Pageable pageable = PageRequest.of(page, size);
            List<CommunityResponse.Main> actual = communityService.getAll("POST", pageable);

            //then
            assertThat(actual).hasSize(2);
        }

        @Test
        @DisplayName("실패: 잘못된 타입")
        void fail() {
            //given
            communityService.create(user.getId(), COMMUNITY_작성_요청);
            communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when, then
            int page = 0;
            int size = 4;
            Pageable pageable = PageRequest.of(page, size);

            final String invalidType = "invalid";
            assertThatThrownBy(() -> communityService.getAll(invalidType, pageable))
                .isInstanceOf(CommunityTypeNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("커뮤니티를 상세조회한다")
    class get {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when
            CommunityResponse.Detail actual = communityService.get(communityId);

            //then
            assertAll(() -> {
                assertThat(actual.title()).isEqualTo("제목");
                assertThat(actual.content()).isEqualTo("내용");
                assertThat(actual.writer()).isEqualTo("남세");
            });
        }

        @Test
        @DisplayName("실패: 존재하지 않는 매거진")
        void fail() {
            //given
            UUID invalidMagazineId = UUID.randomUUID();

            //when, then
            assertThatThrownBy(() -> communityService.get(invalidMagazineId))
                .isInstanceOf(CommunityNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("커뮤니티를 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when
            communityService.update(user.getId(), communityId, COMMUNITY_수정_요청);

            //then
            Community actual = communityRepository.getCommunity(communityId);
            assertAll(() -> {
                assertThat(actual.getTitle()).isEqualTo("새로운 제목");
                assertThat(actual.getContent()).isEqualTo("새로운 내용");
            });
        }

        @Test
        @DisplayName("실패: 잘못된 제목")
        void fail() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when, then
            String invalidTitle = "";
            CommunityRequest.Update updateCommand = CommunityRequest.Update.builder()
                .title(invalidTitle)
                .content("새로운 내용")
                .image(new ImageRequest.Create("path", "url"))
                .build();

            assertThatThrownBy(() -> communityService.update(user.getId(), communityId, updateCommand))
                .isInstanceOf(InvalidCommunityTitleException.class);
        }

        @Test
        @DisplayName("실패: 잘못된 내용")
        void fail2() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when, then
            String invalidContent = "";
            CommunityRequest.Update updateCommand = CommunityRequest.Update.builder()
                .title("새로운 제목")
                .content(invalidContent)
                .image(new ImageRequest.Create("path", "url"))
                .build();

            assertThatThrownBy(() -> communityService.update(user.getId(), communityId, updateCommand))
                .isInstanceOf(InvalidCommunityContentException.class);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 커뮤니티")
        void fail3() {
            //given
            communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when, then
            final UUID invalidCommunityId = UUID.randomUUID();
            CommunityRequest.Update updateCommand = CommunityRequest.Update.builder()
                .title("새로운 제목")
                .content("새로운 내용")
                .image(new ImageRequest.Create("path", "url"))
                .build();

            assertThatThrownBy(() -> communityService.update(user.getId(), invalidCommunityId, updateCommand))
                .isInstanceOf(CommunityNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 다른유저의 커뮤니티")
        void fail4() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            User anotherUser = User.builder()
                .email("anotherUser@gmail.com")
                .name(UserName.create("다른유저"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            jpaUserRepository.save(anotherUser);

            //when, then
            CommunityRequest.Update updateCommand = CommunityRequest.Update.builder()
                .title("새로운 제목")
                .content("새로운 내용")
                .image(new ImageRequest.Create("path", "url"))
                .build();

            assertThatThrownBy(() -> communityService.update(anotherUser.getId(), communityId, updateCommand))
                .isInstanceOf(DifferentUserException.class);
        }
    }

    @Nested
    @DisplayName("커뮤니티를 삭제한다")
    class delete {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when
            communityService.delete(user.getId(), communityId);

            //then
            boolean actual = jpaCommunityRepository.findById(communityId).isPresent();
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 커뮤니티")
        void fail() {
            //given
            communityService.create(user.getId(), COMMUNITY_작성_요청);

            //when, then
            final UUID invalidCommunityId = UUID.randomUUID();
            assertThatThrownBy(() -> communityService.delete(user.getId(), invalidCommunityId))
                .isInstanceOf(CommunityNotFoundException.class);
        }

        @Test
        @DisplayName("실패: 다른유저의 커뮤니티")
        void fail2() {
            //given
            UUID communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

            User anotherUser = User.builder()
                .email("anotherUser@gmail.com")
                .name(UserName.create("다른유저"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            jpaUserRepository.save(anotherUser);

            //when, then
            assertThatThrownBy(() -> communityService.delete(anotherUser.getId(), communityId))
                .isInstanceOf(DifferentUserException.class);
        }
    }
}