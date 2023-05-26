package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.domain.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MagazineTest {

    @DisplayName("제목과 내용을 수정한다.")
    @Test
    void 제목과_내용을_수정한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);

        //when
        Title newTitle = Title.create("새로운 제목");
        Content newContent = Content.create("새로운 내용");
        Writer newWriter = Writer.create("새로운 작성자");
        magazine.update(newTitle, newContent, newWriter);

        //then
        assertAll(() -> {
            assertThat(magazine.getTitle()).isEqualTo("새로운 제목");
            assertThat(magazine.getContent()).isEqualTo("새로운 내용");
            assertThat(magazine.getWriter()).isEqualTo("새로운 작성자");
        });
    }

    @DisplayName("잘못된 제목으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_제목으로_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);

        //when, then
        String invalidTitle = " ";
        Content newContent = Content.create("새로운 내용");
        Writer newWriter = Writer.create("새로운 작성자");

        assertThatThrownBy(() -> magazine.update(Title.create(invalidTitle), newContent, newWriter))
            .isInstanceOf(InvalidMagazineTitleException.class);
    }

    @DisplayName("잘못된 내용으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_내용으로_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);

        //when, then
        Title newTitle = Title.create("새로운 제목");
        String invalidContent = " ";
        Writer newWriter = Writer.create("새로운 작성자");

        assertThatThrownBy(() -> magazine.update(newTitle, Content.create(invalidContent), newWriter))
            .isInstanceOf(InvalidMagazineContentException.class);
    }

    @DisplayName("잘못된 작성자로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_작성자로_수정할_경우_예외가_발생한다() {
        //given
        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Writer writer = Writer.create("작성자");
        Magazine magazine = Magazine.create(title, content, writer);

        //when, then
        Title newTitle = Title.create("새로운 제목");
        Content newContent = Content.create("새로운 내용");
        String invalidWriter = " ";

        assertThatThrownBy(() -> magazine.update(newTitle, newContent, Writer.create(invalidWriter)))
            .isInstanceOf(InvalidMagazineContentException.class);
    }
}