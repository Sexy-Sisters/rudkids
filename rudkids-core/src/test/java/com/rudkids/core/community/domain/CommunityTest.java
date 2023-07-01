package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityContentException;
import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityTest {

    @DisplayName("제목과 내용을 수정한다.")
    @Test
    void 제목과_내용을_수정한다() {
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

    @DisplayName("잘못된 제목으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_제목으로_수정할_경우_예외가_발생한다() {
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

    @DisplayName("잘못된 내용으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_내용으로_수정할_경우_예외가_발생한다() {
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

    @DisplayName("어드민 권한이라면 매거진에 작성할 수 있다.")
    @Test
    void 어드민_권한인_사람은_매거진에_작성할_수_있다() {
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

    @DisplayName("어드민 권한이 아니라면 매거진에 작성할 수 없다.")
    @Test
    void 어드민_권한이_아니라면_매거진에_작성할_수_없다() {
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

    @DisplayName("같은 사용자가 아니라면 예외를 터뜨린다.")
    @Test
    void 같은_사용자가_아니라면_예외를_터뜨린다() {
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