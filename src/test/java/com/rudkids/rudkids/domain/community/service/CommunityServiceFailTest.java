package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityContentException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityTitleException;
import com.rudkids.rudkids.domain.community.exception.InvalidCommunityWriterException;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CommunityServiceFailTest extends MagazineServiceFixtures {

//    @DisplayName("[매거진-생성-InvalidMagazineTitleException]")
//    @Test
//    void 잘못된_제목을_입력하고_글을_작성할_경우_예외가_발생한다() {
//        //given
//        String invalidTitle = "a".repeat(51);
//        CommunityCommand.Create command = CommunityCommand.Create.builder()
//            .title(invalidTitle)
//            .content("내용")
//            .build();
//
//        //when, then
//        assertThatThrownBy(() -> communityService.create(command))
//            .isInstanceOf(InvalidCommunityTitleException.class);
//    }
//
//    @DisplayName("[매거진-생성-InvalidMagazineContentException]")
//    @Test
//    void 잘못된_내용을_입력하고_글을_작성할_경우_예외가_발생한다() {
//        //given
//        String invalidContent = " ";
//        CommunityCommand.Create command = CommunityCommand.Create.builder()
//            .title("제목")
//            .content(invalidContent)
//            .writer("작성자")
//            .build();
//
//        //when, then
//        assertThatThrownBy(() -> communityService.create(command))
//            .isInstanceOf(InvalidCommunityContentException.class);
//    }
//
//    @DisplayName("[매거진-생성-InvalidMagazineWriterException]")
//    @Test
//    void 잘못된_작성자를_입력하고_글을_작성할_경우_예외가_발생한다() {
//        //given
//        String invalidWriter = " ";
//        CommunityCommand.Create command = CommunityCommand.Create.builder()
//            .title("제목")
//            .content("내용")
//            .writer(invalidWriter)
//            .build();
//
//        //when, then
//        assertThatThrownBy(() -> communityService.create(command))
//            .isInstanceOf(InvalidCommunityWriterException.class);
//    }
//
//    @DisplayName("[매거진-수정-InvalidMagazineTitleException]")
//    @Test
//    void 잘못된_제목을_입력하고_글을_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when, then
//        String invalidTitle = "";
//        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
//            .title(invalidTitle)
//            .content("새로운 내용")
//            .writer("새로운 작성자")
//            .build();
//
//        assertThatThrownBy(() -> communityService.update(community.getId(), updateCommand))
//            .isInstanceOf(InvalidCommunityTitleException.class);
//    }
//
//    @DisplayName("[매거진-수정-InvalidMagazineContentException]")
//    @Test
//    void 잘못된_내용을_입력하고_글을_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when, then
//        String invalidContent = "";
//        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
//            .title("새로운 제목")
//            .content(invalidContent)
//            .writer("새로운 작성자")
//            .build();
//
//        assertThatThrownBy(() -> communityService.update(community.getId(), updateCommand))
//            .isInstanceOf(InvalidCommunityContentException.class);
//    }
//
//    @DisplayName("[매거진-수정-InvalidMagazineWriterException]")
//    @Test
//    void 잘못된_작성자를_입력하고_글을_수정할_경우_예외가_발생한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when, then
//        String invalidWriter = "";
//        CommunityCommand.Update updateCommand = CommunityCommand.Update.builder()
//            .title("새로운 제목")
//            .content("새로운 내용")
//            .writer(invalidWriter)
//            .build();
//
//        assertThatThrownBy(() -> communityService.update(community.getId(), updateCommand))
//            .isInstanceOf(InvalidCommunityWriterException.class);
//    }
//
//    @DisplayName("[매거진-상세조회-MagazineNotFoundException]")
//    @Test
//    void 존재하지_않는_매거진_글을_상세조회할_경우_예외가_발생한다() {
//        //given
//        UUID invalidMagazineId = UUID.randomUUID();
//
//        //when, then
//        assertThatThrownBy(() -> communityService.find(invalidMagazineId))
//            .isInstanceOf(CommunityNotFoundException.class);
//    }
}
