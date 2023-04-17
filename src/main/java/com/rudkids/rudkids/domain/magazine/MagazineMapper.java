package com.rudkids.rudkids.domain.magazine;

import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import org.springframework.stereotype.Component;

@Component
public class MagazineMapper {

    public MagazineInfo.Main toMain(Magazine magazine) {
        return MagazineInfo.Main.builder()
                .title(magazine.getTitle())
                .writer(magazine.getWriter())
                .build();
    }

    public MagazineInfo.Detail toDetail(Magazine magazine) {
        return MagazineInfo.Detail.builder()
                .title(magazine.getTitle())
                .writer(magazine.getWriter())
                .content(magazine.getContent())
                .build();
    }
}
