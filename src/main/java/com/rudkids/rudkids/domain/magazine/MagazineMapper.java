package com.rudkids.rudkids.domain.magazine;

import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import org.springframework.stereotype.Component;

@Component
public class MagazineMapper {

    public MagazineInfo.All toInfo(Magazine magazine) {
        return MagazineInfo.All.builder()
                .title(magazine.getTitle())
                .writer(magazine.getWriter())
                .build();
    }
}
