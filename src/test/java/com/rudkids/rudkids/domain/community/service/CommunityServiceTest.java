package com.rudkids.rudkids.domain.community.service;

import com.rudkids.rudkids.common.fixtures.magazine.MagazineServiceFixtures;
import com.rudkids.rudkids.domain.community.CommunityInfo;
import com.rudkids.rudkids.domain.community.domain.Community;
import com.rudkids.rudkids.domain.community.domain.Content;
import com.rudkids.rudkids.domain.community.domain.Title;
import com.rudkids.rudkids.domain.community.exception.CommunityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommunityServiceTest extends MagazineServiceFixtures {

//    @DisplayName("[매거진-생성]")
//    @Test
//    void 관리자는_매거진_글을_작성한다() {
//        //given, when
//        communityService.create(MAGAZINE_작성_요청);
//
//        //then
//        Community actual = communityRepository.findByTitleValue(MAGAZINE_작성_요청.title())
//            .orElseThrow(CommunityNotFoundException::new);
//
//        assertAll(() -> {
//            assertThat(actual.getTitle()).isEqualTo("제목");
//            assertThat(actual.getContent()).isEqualTo("내용");
//            assertThat(actual.getWriter()).isEqualTo("작성자");
//        });
//    }
//
//    @DisplayName("[매거진-수정]")
//    @Test
//    void 관리자는_매거진_글을_수정한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when
//        communityService.update(community.getId(), MAGAZINE_수정_요청);
//
//        //then
//        Community actual = communityRepository.findById(community.getId())
//            .orElseThrow(CommunityNotFoundException::new);
//
//        assertAll(() -> {
//            assertThat(actual.getTitle()).isEqualTo("새로운 제목");
//            assertThat(actual.getContent()).isEqualTo("새로운 내용");
//            assertThat(actual.getWriter()).isEqualTo("새로운 작성자");
//        });
//    }
//
//    @DisplayName("[매거진-삭제]")
//    @Test
//    void 관리자는_매거진_글을_삭제한다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when
//        communityService.delete(community.getId());
//
//        //then
//        boolean actual = communityRepository.findById(community.getId()).isPresent();
//        assertThat(actual).isFalse();
//    }
//
//    @DisplayName("[매거진-전체조회]")
//    @Test
//    void 아무나_매거진_글을_전체조회할_수_있다() {
//        //given
//        communityService.create(MAGAZINE_작성_요청);
//
//        Title newTitle = Title.create("새로운 제목");
//        Content newContent = Content.create("새로운 내용");
//        Writer newWriter = Writer.create("작성자");
//        Community newCommunity = Community.create(newTitle, newContent, newWriter);
//        communityRepository.save(newCommunity);
//
//        //when
//        List<CommunityInfo.Main> actual = communityService.findAll();
//
//        //then
//        assertThat(actual).hasSize(2);
//    }
//
//    @DisplayName("[매거진-상세조회]")
//    @Test
//    void 아무나_매거진_글을_상세조회할_수_있다() {
//        //given
//        Title title = Title.create("제목");
//        Content content = Content.create("내용");
//        Writer writer = Writer.create("작성자");
//        Community community = Community.create(title, content, writer);
//        communityRepository.save(community);
//
//        //when
//        CommunityInfo.Detail actual = communityService.find(community.getId());
//
//        //then
//        assertAll(() -> {
//            assertThat(actual.title()).isEqualTo("제목");
//            assertThat(actual.writer()).isEqualTo("작성자");
//            assertThat(actual.content()).isEqualTo("내용");
//        });
//    }
}