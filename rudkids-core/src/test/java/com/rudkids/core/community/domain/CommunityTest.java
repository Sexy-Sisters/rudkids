package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityContentException;
import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityTest {

    @Nested
    @DisplayName("제목과 내용을 수정한다")
    class update {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when
            Title newTitle = Title.create("새로운 제목");
            Content newContent = Content.create("새로운 내용");
            CommunityImage newImage = CommunityImage.create("path", "url");
            community.update(newTitle, newContent, newImage);

            //then
            assertAll(() -> {
                assertThat(community.getTitle()).isEqualTo("새로운 제목");
                assertThat(community.getContent()).isEqualTo("새로운 내용");
            });
        }

        @Test
        @DisplayName("실패: 잘못된 제목")
        void fail() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when, then
            String invalidTitle = " ";
            Content newContent = Content.create("새로운 내용");
            CommunityImage newImage = CommunityImage.create("path", "url");

            assertThatThrownBy(() -> community.update(Title.create(invalidTitle), newContent, newImage))
                .isInstanceOf(InvalidCommunityTitleException.class);
        }

        @Test
        @DisplayName("실패: 잘못된 내용")
        void fail2() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when, then
            Title newTitle = Title.create("새로운 제목");
            String invalidContent = " ";
            CommunityImage newImage = CommunityImage.create("path", "url");

            assertThatThrownBy(() -> community.update(newTitle, Content.create(invalidContent), newImage))
                .isInstanceOf(InvalidCommunityContentException.class);
        }
    }

    @Nested
    @DisplayName("어드민은 매거진을 작성할 수 있다")
    class choiceType {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            user.changeAuthorityAdmin();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when
            community.choiceType("MAGAZINE");

            //then
            assertThat(community.getCommunityType()).isEqualTo(CommunityType.MAGAZINE);
        }

        @Test
        @DisplayName("성공: 일반유저가 작성하면 자동으로 게시판 상태로 변경된다")
        void success2() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when
            community.choiceType("MAGAZINE");

            //then
            assertThat(community.getCommunityType()).isEqualTo(CommunityType.POST);
        }
    }

    @Nested
    @DisplayName("같은 유저인지 검증한다")
    class validateHasSameUser {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when, then
            community.validateHasSameUser(user);
        }

        @Test
        @DisplayName("실패: 다른유저")
        void fail() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Title title = Title.create("제목");
            Content content = Content.create("내용");
            CommunityImage image = CommunityImage.create("path", "url");
            Community community = Community.create(user, title, content, image);

            //when, then
            User anotherUser = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();

            assertThatThrownBy(() -> community.validateHasSameUser(anotherUser))
                .isInstanceOf(DifferentUserException.class);
        }
    }
}