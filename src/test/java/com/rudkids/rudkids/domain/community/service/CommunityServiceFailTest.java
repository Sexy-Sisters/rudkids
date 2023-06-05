package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.common.fixtures.community.CommunityServiceFixtures;
import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import com.rudkids.rudkids.domain.community.exception.CommunityTypeNotFoundException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityContentException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityTitleException;
import com.rudkids.rudkids.domain.user.domain.*;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CommunityServiceFailTest extends CommunityServiceFixtures {

    @DisplayName("[커뮤니티-생성-InvalidCommunityTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_작성할_경우_예외가_발생한다() {
        //given
        String invalidTitle = "a".repeat(51);
        CommunityCommand.Create command = CommunityCommand.Create.builder()
            .title(invalidTitle)
            .content("내용")
            .type("POST")
            .build();

        //when, then
        assertThatThrownBy(() -> communityService.create(user.getId(), command))
            .isInstanceOf(InvalidCommunityTitleException.class);
    }

    @DisplayName("[커뮤니티-생성-InvalidCommunityContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_작성할_경우_예외가_발생한다() {
        //given
        String invalidContent = " ";
        CommunityCommand.Create command = CommunityCommand.Create.builder()
            .title("제목")
            .content(invalidContent)
            .type("POST")
            .build();

        //when, then
        assertThatThrownBy(() -> communityService.create(user.getId(), command))
            .isInstanceOf(InvalidCommunityContentException.class);
    }

    @DisplayName("[커뮤니티-생성-CommunityTypeNotFoundException]")
    @Test
    void 어드민_사용자가_잘못된_타입을_입력하고_매거진글을_작성할_경우_예외가_발생한다() {
        //given
        var profileImage = ProfileImage.create("path", "url");
        User admin = User.builder()
            .email("namse@gmail.com")
            .name(UserName.create("남세"))
            .age(18)
            .gender("MALE")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        admin.changeAuthorityAdmin();
        userRepository.save(admin);

        String invalidType = " ";
        CommunityCommand.Create command = CommunityCommand.Create.builder()
            .title("제목")
            .content("내용")
            .type(invalidType)
            .build();

        //when, then
        assertThatThrownBy(() -> communityService.create(admin.getId(), command))
            .isInstanceOf(CommunityTypeNotFoundException.class);
    }

    @DisplayName("[커뮤니티-수정-InvalidMagazineTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when, then
        String invalidTitle = "";
        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
            .title(invalidTitle)
            .content("새로운 내용")
            .build();

        assertThatThrownBy(() -> communityService.update(user.getId(), communityId, updateCommand))
            .isInstanceOf(InvalidCommunityTitleException.class);
    }

    @DisplayName("[커뮤니티-수정-InvalidCommunityContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when, then
        String invalidContent = "";
        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
            .title("새로운 제목")
            .content(invalidContent)
            .build();

        assertThatThrownBy(() -> communityService.update(user.getId(), communityId, updateCommand))
            .isInstanceOf(InvalidCommunityContentException.class);
    }

    @DisplayName("[커뮤니티-수정-DifferentUserException]")
    @Test
    void 다른_유저의_글을_수정할_경우_예외가_발생한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when, then
        var profileImage = ProfileImage.create("path", "url");
        User anotherUser = User.builder()
            .email("another@gmail.com")
            .name(UserName.create("다른유저"))
            .age(18)
            .gender("MALE")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .profileImage(profileImage)
            .socialType(SocialType.GOOGLE)
            .build();
        userRepository.save(anotherUser);
        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
            .title("새로운 제목")
            .content("새로운 내용")
            .build();

        assertThatThrownBy(() -> communityService.update(anotherUser.getId(), communityId, updateCommand))
            .isInstanceOf(DifferentUserException.class);
    }

    @DisplayName("[매거진-상세조회-MagazineNotFoundException]")
    @Test
    void 존재하지_않는_매거진_글을_상세조회할_경우_예외가_발생한다() {
        //given
        UUID invalidMagazineId = UUID.randomUUID();

        //when, then
        assertThatThrownBy(() -> communityService.find(invalidMagazineId))
            .isInstanceOf(CommunityNotFoundException.class);
    }
}
