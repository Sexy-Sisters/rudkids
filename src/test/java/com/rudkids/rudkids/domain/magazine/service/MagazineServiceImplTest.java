package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.magazine.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MagazineServiceImplTest extends MagazineServiceFixtures {

    @DisplayName("관리자는 매거진 글을 작성한다.")
    @Test
    void 관리자는_매거진_글을_작성한다() {
        //given
        MagazineCommand.Create command = MagazineCommand.Create.builder()
                .title("제목")
                .content("내용")
                .build();

        //when
        magazineService.create(admin.getId(), command);

        //then
        Magazine actual = magazineRepository.findByTitleValue(command.title())
                .orElseThrow(MagazineNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("제목");
            assertThat(actual.getContent()).isEqualTo("내용");
            admin.validateAdminRole();
        });
    }

    @DisplayName("잘못된 제목을 입력하고 글을 작성할 경우 예외가 발생한다.")
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

    @DisplayName("잘못된 내용을 입력하고 글을 작성할 경우 예외가 발생한다.")
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

    @DisplayName("일반사용자가 매거진 글을 작성할 경우 예외가 발생한다.")
    @Test
    void 일반사용자가_매거진_글을_작성할_경우_예외가_발생한다() {
        //given
        MagazineCommand.Create command = MagazineCommand.Create.builder()
                .title("제목")
                .content("내용")
                .build();

        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        //when, then
        assertThatThrownBy(() -> magazineService.create(user.getId(), command))
                .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("파트너가 매거진 글을 작성할 경우 예외가 발생한다.")
    @Test
    void 파트너가_매거진_글을_작성할_경우_예외가_발생한다() {
        //given
        MagazineCommand.Create command = MagazineCommand.Create.builder()
                .title("제목")
                .content("내용")
                .build();

        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        user.changeAuthorityPartner();
        userRepository.save(user);

        //when, then
        assertThatThrownBy(() -> magazineService.create(user.getId(), command))
                .isInstanceOf(NotAdminRoleException.class);
    }
}