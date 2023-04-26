package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineInfo;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
                .phoneNumber("01029401509")
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
                .phoneNumber("01029401509")
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
}