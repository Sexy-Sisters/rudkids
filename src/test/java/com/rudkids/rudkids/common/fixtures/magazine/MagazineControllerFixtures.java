package com.rudkids.rudkids.common.fixtures.magazine;

import com.rudkids.rudkids.interfaces.magazine.dto.MagazineRequest;

public class MagazineControllerFixtures {

    public static final String MAGAZINE_DEFAULT_URL = "/api/v1/magazine";
    public static final String MAGAZINE_제목 = "제목";
    public static final String MAGAZINE_내용 = "내용";

    public static MagazineRequest.Create MAGAZINE_작성_요청() {
        return new MagazineRequest.Create(MAGAZINE_제목, MAGAZINE_내용);
    }
}
