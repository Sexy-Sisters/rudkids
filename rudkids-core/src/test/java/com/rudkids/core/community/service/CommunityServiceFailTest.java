package com.rudkids.core.community.service;

import com.rudkids.core.common.fixtures.CommunityServiceFixtures;
import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.exception.CommunityNotFoundException;
import com.rudkids.core.community.exception.CommunityTypeNotFoundException;
import com.rudkids.core.community.exception.InvalidCommunityContentException;
import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.user.domain.*;
import com.rudkids.core.user.exception.DifferentUserException;
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

    @DisplayName("[커뮤니티-생성-InvalidCommunityContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_작성할_경우_예외가_발생한다() {
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

    @DisplayName("[커뮤니티-생성-CommunityTypeNotFoundException]")
    @Test
    void 어드민_사용자가_잘못된_타입을_입력하고_매거진글을_작성할_경우_예외가_발생한다() {
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

    @DisplayName("[커뮤니티-수정-InvalidMagazineTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_수정할_경우_예외가_발생한다() {
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

    @DisplayName("[커뮤니티-수정-InvalidCommunityContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_수정할_경우_예외가_발생한다() {
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

    @DisplayName("[커뮤니티-수정-DifferentUserException]")
    @Test
    void 다른_유저의_글을_수정할_경우_예외가_발생한다() {
        //given
        var communityId = communityService.create(user.getId(), COMMUNITY_작성_요청);

        //when, then
        User anotherUser = User.builder()
            .email("anotherUser@gmail.com")
            .name(UserName.create("다른유저"))
            .profileImage(ProfileImage.create("path", "url"))
            .build();
        jpaUserRepository.save(anotherUser);
        CommunityRequest.Update updateCommand = CommunityRequest.Update.builder()
            .title("새로운 제목")
            .content("새로운 내용")
            .image(new ImageRequest.Create("path", "url"))
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
        assertThatThrownBy(() -> communityService.get(invalidMagazineId))
            .isInstanceOf(CommunityNotFoundException.class);
    }
}
