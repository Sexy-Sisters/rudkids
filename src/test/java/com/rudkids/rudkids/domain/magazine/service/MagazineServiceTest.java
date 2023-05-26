package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineInfo;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.domain.Writer;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MagazineServiceTest extends MagazineServiceFixtures {

    @DisplayName("[매거진-생성]")
    @Test
    void 관리자는_매거진_글을_작성한다() {
        //given, when
        magazineService.create(MAGAZINE_작성_요청);

        //then
        Magazine actual = magazineRepository.findByTitleValue(MAGAZINE_작성_요청.title())
            .orElseThrow(MagazineNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("제목");
            assertThat(actual.getContent()).isEqualTo("내용");
            assertThat(actual.getWriter()).isEqualTo("작성자");
        });
    }

    @DisplayName("[매거진-수정]")
    @Test
    void 관리자는_매거진_글을_수정한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when
        magazineService.update(magazine.getId(), MAGAZINE_수정_요청);

        //then
        Magazine actual = magazineRepository.findById(magazine.getId())
            .orElseThrow(MagazineNotFoundException::new);

        assertAll(() -> {
            assertThat(actual.getTitle()).isEqualTo("새로운 제목");
            assertThat(actual.getContent()).isEqualTo("새로운 내용");
            assertThat(actual.getContent()).isEqualTo("새로운 작성자");
        });
    }

    @DisplayName("[매거진-삭제]")
    @Test
    void 관리자는_매거진_글을_삭제한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when
        magazineService.delete(magazine.getId());

        //then
        boolean actual = magazineRepository.findById(magazine.getId()).isPresent();
        assertThat(actual).isFalse();
    }

    @DisplayName("[매거진-전체조회]")
    @Test
    void 아무나_매거진_글을_전체조회할_수_있다() {
        //given
        magazineService.create(MAGAZINE_작성_요청);

        Title newTitle = Title.create("새로운 제목");
        Content newContent = Content.create("새로운 내용");
        Writer newWriter = Writer.create("작성자");
        Magazine newMagazine = Magazine.create(newTitle, newContent, newWriter);
        magazineRepository.save(newMagazine);

        //when
        List<MagazineInfo.Main> actual = magazineService.findAll();

        //then
        assertThat(actual).hasSize(2);
    }

    @DisplayName("[매거진-상세조회]")
    @Test
    void 아무나_매거진_글을_상세조회할_수_있다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when
        MagazineInfo.Detail actual = magazineService.find(magazine.getId());

        //then
        assertAll(() -> {
            assertThat(actual.title()).isEqualTo("제목");
            assertThat(actual.writer()).isEqualTo("작성자");
            assertThat(actual.content()).isEqualTo("내용");
        });
    }
}