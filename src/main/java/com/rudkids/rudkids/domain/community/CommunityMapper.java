package com.rudkids.rudkids.domain.community;

import com.rudkids.rudkids.domain.community.domain.Community;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {

    public CommunityInfo.Main toMain(Community community) {
        return CommunityInfo.Main.builder()
                .title(community.getTitle())
//                .writer(community.getWriter())
                .build();
    }

    public CommunityInfo.Detail toDetail(Community community) {
        return CommunityInfo.Detail.builder()
                .title(community.getTitle())
//                .writer(community.getWriter())
                .content(community.getContent())
                .build();
    }
}
