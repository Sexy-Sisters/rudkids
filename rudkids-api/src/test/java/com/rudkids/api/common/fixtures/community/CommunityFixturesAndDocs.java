package com.rudkids.api.common.fixtures.community;

import com.rudkids.core.community.dto.CommunityRequest;
import com.rudkids.core.community.dto.CommunityResponse;
import com.rudkids.core.image.dto.ImageRequest;
import com.rudkids.core.image.dto.ImageResponse;

import java.util.List;
import java.util.UUID;

public class CommunityFixturesAndDocs {

    public static final String COMMUNITY_DEFAULT_URL = "/api/v1/community";
    public static final UUID COMMUNITY_ID = UUID.randomUUID();
    public static final String COMMUNITY_제목 = "제목";
    public static final String COMMUNITY_내용 = "내용";
    public static final String COMMUNITY_타입 = "POST";
    public static final String COMMUNITY_작성자 = "작성자";
    public static final String COMMUNITY_작성자_이미지 = "url";
    public static final String COMMUNITY_글_썸네일 = "http://";
    public static final int COMMUNITY_좋아요_개수 = 3;
    public static final ImageRequest.Create COMMUNITY_이미지_요청 = new ImageRequest.Create("path", "url");

    public static CommunityRequest.Create COMMUNITY_작성_요청() {
        return new CommunityRequest.Create(COMMUNITY_제목, COMMUNITY_내용, COMMUNITY_타입, COMMUNITY_이미지_요청);
    }

    public static CommunityRequest.Update COMMUNITY_수정_요청() {
        return new CommunityRequest.Update("새로운 제목", "새로운 내용", COMMUNITY_이미지_요청);
    }

    public static List<CommunityResponse.Main> COMMUNITY_전체조회_응답() {
        return List.of(new CommunityResponse.Main(COMMUNITY_제목, COMMUNITY_작성자, COMMUNITY_글_썸네일));
    }

    public static CommunityResponse.Detail COMMUNITY_상세조회_응답() {
        return CommunityResponse.Detail.builder()
            .title(COMMUNITY_제목)
            .content(COMMUNITY_내용)
            .writer(COMMUNITY_작성자)
            .image(new ImageResponse.Info(COMMUNITY_글_썸네일, COMMUNITY_글_썸네일))
            .writerProfileImage(COMMUNITY_작성자_이미지)
            .likeCount(COMMUNITY_좋아요_개수)
            .build();
    }
}
