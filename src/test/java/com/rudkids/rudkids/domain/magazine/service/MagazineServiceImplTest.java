package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.MagazineInfo;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MagazineServiceImplTest extends MagazineServiceFixtures {

    @DisplayName("관리자는 매거진 글을 작성한다.")
    @Test
    void 관리자는_매거진_글을_작성한다() {
        //given, when
        magazineService.create(admin.getId(), MAGAZINE_작성_요청);

        //then
        Magazine actual = magazineRepository.findByTitleValue(MAGAZINE_작성_요청.title())
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
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        //when, then
        assertThatThrownBy(() -> magazineService.create(user.getId(), MAGAZINE_작성_요청))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("파트너가 매거진 글을 작성할 경우 예외가 발생한다.")
    @Test
    void 파트너가_매거진_글을_작성할_경우_예외가_발생한다() {
        //given
        User partner = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        partner.changeAuthorityPartner();
        userRepository.save(partner);

        //when, then
        assertThatThrownBy(() -> magazineService.create(partner.getId(), MAGAZINE_작성_요청))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("관리자는 매거진 글을 수정한다.")
    @Test
    void 관리자는_매거진_글을_수정한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when
        magazineService.update(admin.getId(), magazine.getId(), MAGAZINE_수정_요청);

        //then
        Magazine actual = magazineRepository.findById(magazine.getId())
            .orElseThrow(MagazineNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("새로운 제목");
            assertThat(actual.getContent()).isEqualTo("새로운 내용");
            admin.validateAdminRole();
        });
    }

    @DisplayName("잘못된 제목을 입력하고 글을 수정할 경우 예외가 발생한다.")
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

        assertThatThrownBy(() -> magazineService.update(admin.getId(), magazine.getId(), updateCommand))
            .isInstanceOf(InvalidMagazineTitleException.class);
    }

    @DisplayName("잘못된 내용을 입력하고 글을 수정할 경우 예외가 발생한다.")
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

        assertThatThrownBy(() -> magazineService.update(admin.getId(), magazine.getId(), updateCommand))
            .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("일반사용자가 매거진 글을 수정할 경우 예외가 발생한다.")
    @Test
    void 일반사용자가_매거진_글을_수정할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(user, title, content);
        magazineRepository.save(magazine);

        //when, then
        assertThatThrownBy(() -> magazineService.update(user.getId(), magazine.getId(), MAGAZINE_수정_요청))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("파트너가 매거진 글을 수정할 경우 예외가 발생한다.")
    @Test
    void 파트너가_매거진_글을_수정할_경우_예외가_발생한다() {
        //given
        User partner = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        partner.changeAuthorityPartner();
        userRepository.save(partner);

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(partner, title, content);
        magazineRepository.save(magazine);

        //when, then
        assertThatThrownBy(() -> magazineService.update(partner.getId(), magazine.getId(), MAGAZINE_수정_요청))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("다른 관리자도 매거진을 수정할 수 있다.")
    @Test
    void 다른_관리자도_매거진을_수정할_수_있다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when
        User anotherAdmin = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        anotherAdmin.changeAuthorityAdmin();
        userRepository.save(anotherAdmin);
        magazineService.update(anotherAdmin.getId(), magazine.getId(), MAGAZINE_수정_요청);

        //then
        Magazine actual = magazineRepository.findById(magazine.getId())
            .orElseThrow(MagazineNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("새로운 제목");
            assertThat(actual.getContent()).isEqualTo("새로운 내용");
            assertThat(actual.getUser()).isNotEqualTo(anotherAdmin);
        });
    }

    @DisplayName("관리자는 매거진 글을 삭제한다.")
    @Test
    void 관리자는_매거진_글을_삭제한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when
        magazineService.delete(admin.getId(), magazine.getId());

        //then
        boolean actual = magazineRepository.findById(magazine.getId()).isPresent();

        assertThat(actual).isFalse();
        admin.validateAdminRole();
    }

    @DisplayName("일반사용자가 매거진 글을 삭제할 경우 예외가 발생한다.")
    @Test
    void 일반사용자가_매거진_글을_삭제할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        userRepository.save(user);

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(user, title, content);
        magazineRepository.save(magazine);

        //when, then
        assertThatThrownBy(() -> magazineService.delete(user.getId(), magazine.getId()))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("파트너가 매거진 글을 삭제할 경우 예외가 발생한다.")
    @Test
    void 파트너가_매거진_글을_삭제할_경우_예외가_발생한다() {
        //given
        User partner = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        partner.changeAuthorityPartner();
        userRepository.save(partner);

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(partner, title, content);
        magazineRepository.save(magazine);

        //when, then
        assertThatThrownBy(() -> magazineService.delete(partner.getId(), magazine.getId()))
            .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("다른 관리자도 매거진을 삭제할 수 있다.")
    @Test
    void 다른_관리자도_매거진을_삭제할_수_있다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when
        User anotherAdmin = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(18)
                .gender("MALE")
                .socialType(SocialType.GOOGLE)
                .build();
        anotherAdmin.changeAuthorityAdmin();
        userRepository.save(anotherAdmin);
        magazineService.delete(anotherAdmin.getId(), magazine.getId());

        //then
        boolean actual = magazineRepository.findById(magazine.getId()).isPresent();

        assertAll(() -> {
            assertThat(actual).isFalse();
            assertThat(magazine.getUser()).isNotEqualTo(anotherAdmin);
        });
    }

    @DisplayName("아무나 매거진 글을 전체조회할 수 있다.")
    @Test
    void 아무나_매거진_글을_전체조회할_수_있다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        Title newTitle = Title.create("새로운 제목");
        Content newContent = Content.create("새로운 내용");
        Magazine newMagazine = Magazine.create(admin, newTitle, newContent);
        magazineRepository.save(newMagazine);

        //when
        List<MagazineInfo.Main> actual = magazineService.findAll();

        //then
        assertThat(actual).hasSize(2);
    }

    @DisplayName("아무나 매거진 글을 상세조회할 수 있다.")
    @Test
    void 아무나_매거진_글을_상세조회할_수_있다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(admin, title, content);
        magazineRepository.save(magazine);

        //when
        MagazineInfo.Detail actual = magazineService.find(magazine.getId());

        //then
        assertAll(() -> {
            assertThat(actual.title()).isEqualTo("제목");
            assertThat(actual.writer()).isEqualTo(admin.getName());
            assertThat(actual.content()).isEqualTo("내용");
        });
    }

    @DisplayName("존재하지 않는 매거진 글을 상세조회할 경우 예외가 발생한다.")
    @Test
    void 존재하지_않는_매거진_글을_상세조회할_경우_예외가_발생한다() {
        //given
        UUID invalidMagazineId = UUID.randomUUID();

        //when, then
        assertThatThrownBy(() -> magazineService.find(invalidMagazineId))
            .isInstanceOf(MagazineNotFoundException.class);
    }
}