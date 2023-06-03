package com.rudkids.rudkids.domain.magazine.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.domain.Writer;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineWriterException;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
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
        assertThatThrownBy(() -> magazineService.create(command))
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
            .writer("작성자")
            .build();

        //when, then
        assertThatThrownBy(() -> magazineService.create(command))
            .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("[매거진-생성-InvalidMagazineWriterException]")
    @Test
    void 잘못된_작성자를_입력하고_글을_작성할_경우_예외가_발생한다() {
        //given
        String invalidWriter = " ";
        MagazineCommand.Create command = MagazineCommand.Create.builder()
            .title("제목")
            .content("내용")
            .writer(invalidWriter)
            .build();

        //when, then
        assertThatThrownBy(() -> magazineService.create(command))
            .isInstanceOf(InvalidMagazineWriterException.class);
    }

    @DisplayName("[매거진-수정-InvalidMagazineTitleException]")
    @Test
    void 잘못된_제목을_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when, then
        String invalidTitle = "";
        MagazineCommand.Update updateCommand = MagazineCommand.Update.builder()
            .title(invalidTitle)
            .content("새로운 내용")
            .writer("새로운 작성자")
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
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when, then
        String invalidContent = "";
        MagazineCommand.Update updateCommand = MagazineCommand.Update.builder()
            .title("새로운 제목")
            .content(invalidContent)
            .writer("새로운 작성자")
            .build();

        assertThatThrownBy(() -> magazineService.update(magazine.getId(), updateCommand))
            .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("[매거진-수정-InvalidMagazineWriterException]")
    @Test
    void 잘못된_작성자를_입력하고_글을_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);
        magazineRepository.save(magazine);

        //when, then
        String invalidWriter = "";
        MagazineCommand.Update updateCommand = MagazineCommand.Update.builder()
            .title("새로운 제목")
            .content("새로운 내용")
            .writer(invalidWriter)
            .build();

        assertThatThrownBy(() -> magazineService.update(magazine.getId(), updateCommand))
            .isInstanceOf(InvalidMagazineWriterException.class);
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
