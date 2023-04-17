package com.rudkids.rudkids.common.fixtures.magazine;

import com.rudkids.rudkids.domain.magazine.MagazineInfo;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineRequest;

import java.util.List;
import java.util.UUID;

public class MagazineControllerFixtures {

    public static final String MAGAZINE_DEFAULT_URL = "/api/v1/magazine";
    public static final UUID MAGAZINE_ID = UUID.randomUUID();
    public static final String MAGAZINE_제목 = "제목";
    public static final String MAGAZINE_내용 = "내용";
    public static final String MAGAZINE_새로운_제목 = "새로운 제목";
    public static final String MAGAZINE_새로운_내용 = "새로운 내용";
    public static final String MAGAZINE_작성자 = "작성자";

    public static MagazineRequest.Create MAGAZINE_작성_요청() {
        return new MagazineRequest.Create(MAGAZINE_제목, MAGAZINE_내용);
    }

    public static MagazineRequest.Create MAGAZINE_수정_요청() {
        return new MagazineRequest.Create(MAGAZINE_새로운_제목, MAGAZINE_새로운_내용);
    }

    public static List<MagazineInfo.All> MAGAZINE_전체조회_응답() {
        return List.of(new MagazineInfo.All(MAGAZINE_제목, MAGAZINE_작성자));
    }
}
