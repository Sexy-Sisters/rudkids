package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineTitleException;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
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
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(user, title, content);

        //when
        Title newTitle = Title.create("새로운 제목");
        Content newContent = Content.create("새로운 내용");
        magazine.update(newTitle, newContent);

        //then
        assertAll(() -> {
            assertThat(magazine.getTitle()).isEqualTo("새로운 제목");
            assertThat(magazine.getContent()).isEqualTo("새로운 내용");
        });
    }

    @DisplayName("잘못된 제목으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_제목으로_수정할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(user, title, content);

        //when, then
        String invalidTitle = " ";
        Content newContent = Content.create("새로운 내용");

        assertThatThrownBy(() -> magazine.update(Title.create(invalidTitle), newContent))
                .isInstanceOf(InvalidMagazineTitleException.class);
    }

    @DisplayName("잘못된 내용으로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_내용으로_수정할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();

        Title title = Title.create("제목");
        Content content = Content.create("내용");
        Magazine magazine = Magazine.create(user, title, content);

        //when, then
        Title newTitle = Title.create("새로운 제목");
        String invalidContent = " ";

        assertThatThrownBy(() -> magazine.update(newTitle, Content.create(invalidContent)))
                .isInstanceOf(InvalidMagazineContentException.class);
    }
}