package com.rudkids.rudkids.common.fixtures.magazine;

import com.rudkids.rudkids.domain.community.CommunityInfo;
import com.rudkids.rudkids.interfaces.community.dto.CommunityRequest;

import java.util.List;
import java.util.UUID;

public class MagazineControllerFixtures {

    public static final String MAGAZINE_DEFAULT_URL = "/api/v1/magazine";
    public static final UUID MAGAZINE_ID = UUID.randomUUID();
    public static final String MAGAZINE_제목 = "제목";
    public static final String MAGAZINE_내용 = "내용";
    public static final String MAGAZINE_작성자 = "작성자";
    public static final String MAGAZINE_새로운_제목 = "새로운 제목";
    public static final String MAGAZINE_새로운_내용 = "새로운 내용";
    public static final String MAGAZINE_새로운_작성자 = "새로운 작성자";

    public static CommunityRequest.Create MAGAZINE_작성_요청() {
        return new CommunityRequest.Create(MAGAZINE_제목, MAGAZINE_내용, MAGAZINE_작성자, "");
    }

    public static CommunityRequest.Create MAGAZINE_수정_요청() {
        return new CommunityRequest.Create(MAGAZINE_새로운_제목, MAGAZINE_새로운_내용, MAGAZINE_새로운_작성자, "");
    }

    public static List<CommunityInfo.Main> MAGAZINE_전체조회_응답() {
        return List.of(new CommunityInfo.Main(MAGAZINE_제목, MAGAZINE_작성자));
    }

    public static CommunityInfo.Detail MAGAZINE_상세조회_응답() {
        return new CommunityInfo.Detail(MAGAZINE_제목, MAGAZINE_작성자, MAGAZINE_내용);
    }
}
