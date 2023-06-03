package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.domain.community.exception.InvalidCommunityContentException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityTitleException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityWriterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityTest {

//    @DisplayName("제목과 내용을 수정한다.")
//    @Test
//    void 제목과_내용을_수정한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//
//        //when
//        Title newTitle = Title.create("새로운 제목");
//        Content newContent = Content.create("새로운 내용");
//        Writer newWriter = Writer.create("새로운 작성자");
//        community.update(newTitle, newContent, newWriter);
//
//        //then
//        assertAll(() -> {
//            assertThat(community.getTitle()).isEqualTo("새로운 제목");
//            assertThat(community.getContent()).isEqualTo("새로운 내용");
//            assertThat(community.getWriter()).isEqualTo("새로운 작성자");
//        });
//    }
//
//    @DisplayName("잘못된 제목으로 수정할 경우 예외가 발생한다.")
//    @Test
//    void 잘못된_제목으로_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//
//        //when, then
//        String invalidTitle = " ";
//        Content newContent = Content.create("새로운 내용");
//        Writer newWriter = Writer.create("새로운 작성자");
//
//        assertThatThrownBy(() -> community.update(Title.create(invalidTitle), newContent, newWriter))
//            .isInstanceOf(InvalidCommunityTitleException.class);
//    }
//
//    @DisplayName("잘못된 내용으로 수정할 경우 예외가 발생한다.")
//    @Test
//    void 잘못된_내용으로_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//
//        //when, then
//        Title newTitle = Title.create("새로운 제목");
//        String invalidContent = " ";
//        Writer newWriter = Writer.create("새로운 작성자");
//
//        assertThatThrownBy(() -> community.update(newTitle, Content.create(invalidContent), newWriter))
//            .isInstanceOf(InvalidCommunityContentException.class);
//    }
//
//    @DisplayName("잘못된 작성자로 수정할 경우 예외가 발생한다.")
//    @Test
//    void 잘못된_작성자로_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//
//        //when, then
//        Title newTitle = Title.create("새로운 제목");
//        Content newContent = Content.create("새로운 내용");
//        String invalidWriter = " ";
//
//        assertThatThrownBy(() -> community.update(newTitle, newContent, Writer.create(invalidWriter)))
//            .isInstanceOf(InvalidCommunityWriterException.class);
//    }
}