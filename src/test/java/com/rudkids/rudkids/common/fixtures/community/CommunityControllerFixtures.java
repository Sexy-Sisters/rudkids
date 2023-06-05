package com.rudkids.rudkids.common.fixtures.community;

import com.rudkids.rudkids.domain.community.CommunityInfo;
import com.rudkids.rudkids.interfaces.community.dto.CommunityRequest;

import java.util.List;
import java.util.UUID;

public class CommunityControllerFixtures {

    public static final String COMMUNITY_DEFAULT_URL = "/api/v1/community";
    public static final UUID COMMUNITY_ID = UUID.randomUUID();
    public static final String COMMUNITY_제목 = "제목";
    public static final String COMMUNITY_내용 = "내용";
    public static final String COMMUNITY_타입 = "POST";
    public static final String COMMUNITY_작성자 = "작성자";
    public static final String COMMUNITY_작성자_이미지 = "작성자";

    public static CommunityRequest.Create COMMUNITY_작성_요청() {
        return new CommunityRequest.Create(COMMUNITY_제목, COMMUNITY_내용, COMMUNITY_타입);
    }

    public static CommunityRequest.Update COMMUNITY_수정_요청() {
        return new CommunityRequest.Update("새로운 제목", "새로운 내용");
    }

    public static List<CommunityInfo.Main> COMMUNITY_전체조회_응답() {
        return List.of(new CommunityInfo.Main(COMMUNITY_제목, COMMUNITY_작성자));
    }

    public static CommunityInfo.Detail COMMUNITY_상세조회_응답() {
        return new CommunityInfo.Detail(COMMUNITY_제목, COMMUNITY_내용, COMMUNITY_작성자, COMMUNITY_작성자_이미지);
    }
}
