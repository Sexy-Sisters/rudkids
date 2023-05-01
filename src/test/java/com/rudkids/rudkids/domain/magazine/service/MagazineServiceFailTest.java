package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.domain.UserName;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MagazineServiceFailTest extends MagazineServiceFixtures {

    @DisplayName("[매거진-생성-InvalidMagazineTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_작성할_경우_예외가_발생한다() {
        //given
        String invalidTitle = "a".repeat(51);
        MagazineCommand.Create command = MagazineCommand.Create.builder()
                .title(invalidTitle)
                .content("내용")
                .build();

        //when, then
        assertThatThrownBy(() -> magazineService.create(admin.getId(), command))
                .isInstanceOf(InvalidMagazineTitleException.class);
    }

    @DisplayName("[매거진-생성-InvalidMagazineContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_작성할_경우_예외가_발생한다() {
        //given
        String invalidContent = " ";
        MagazineCommand.Create command = MagazineCommand.Create.builder()
                .title("제목")
                .content(invalidContent)
                .build();

        //when, then
        assertThatThrownBy(() -> magazineService.create(admin.getId(), command))
                .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("[매거진-생성-NotAdminRoleException]")
    @Test
    void 일반사용자가_매거진_글을_작성할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .age(18)
                .gender("MALE")
                .phoneNumber(PhoneNumber.create("010-2940-1509"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        //when, then
        assertThatThrownBy(() -> magazineService.create(user.getId(), MAGAZINE_작성_요청))
                .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("[매거진-생성-NotAdminRoleException]")
    @Test
    void 파트너가_매거진_글을_작성할_경우_예외가_발생한다() {
        //given
        User partner = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .age(18)
                .gender("MALE")
                .phoneNumber(PhoneNumber.create("010-2940-1509"))
                .socialType(SocialType.GOOGLE)
                .build();
        partner.changeAuthorityPartner();
        userRepository.save(partner);

        //when, then
        assertThatThrownBy(() -> magazineService.create(partner.getId(), MAGAZINE_작성_요청))
                .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("[매거진-수정-InvalidMagazineTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when, then
        String invalidTitle = "";
        MagazineCommand.Update updateCommand = MagazineCommand.Update.builder()
                .title(invalidTitle)
                .content("새로운 내용")
                .build();

        assertThatThrownBy(() -> magazineService.update(magazine.getId(), updateCommand))
                .isInstanceOf(InvalidMagazineTitleException.class);
    }

    @DisplayName("[매거진-수정-InvalidMagazineContentException]")
    @Test
    void 잘못된_내용을_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when, then
        String invalidContent = "";
        MagazineCommand.Update updateCommand = MagazineCommand.Update.builder()
                .title("새로운 제목")
                .content(invalidContent)
                .build();

        assertThatThrownBy(() -> magazineService.update(magazine.getId(), updateCommand))
                .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("[매거진-상세조회-MagazineNotFoundException]")
    @Test
    void 존재하지_않는_매거진_글을_상세조회할_경우_예외가_발생한다() {
        //given
        UUID invalidMagazineId = UUID.randomUUID();

        //when, then
        assertThatThrownBy(() -> magazineService.find(invalidMagazineId))
                .isInstanceOf(MagazineNotFoundException.class);
    }
}
