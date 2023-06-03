package com.rudkids.rudkids.common.fixtures.magazine;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.community.CommunityCommand;
import com.rudkids.rudkids.domain.community.service.CommunityService;
import com.rudkids.rudkids.infrastructure.community.CommunityRepository;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class MagazineServiceFixtures {

    @Autowired
    protected CommunityService communityService;

    @Autowired
    protected CommunityRepository communityRepository;

    @Autowired
    protected UserRepository userRepository;

    protected CommunityCommand.Create MAGAZINE_작성_요청 = CommunityCommand.Create.builder()
        .title("제목")
        .content("내용")
        .writer("작성자")
        .build();

    protected CommunityCommand.Update MAGAZINE_수정_요청 = CommunityCommand.Update.builder()
        .title("새로운 제목")
        .content("새로운 내용")
        .writer("새로운 작성자")
        .build();
}
