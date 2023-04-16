package com.rudkids.rudkids.common.fixtures.magazine;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.domain.user.domain.Age;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.infrastructure.magazine.MagazineRepository;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class MagazineServiceFixtures {

    @Autowired
    protected MagazineService magazineService;

    @Autowired
    protected MagazineRepository magazineRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User admin;

    protected MagazineCommand.Create MAGAZINE_작성_요청 = MagazineCommand.Create.builder()
            .title("제목")
            .content("내용")
            .build();

    protected MagazineCommand.Update MAGAZINE_수정_요청 = MagazineCommand.Update.builder()
            .title("새로운 제목")
            .content("새로운 내용")
            .build();

    @BeforeEach
    void setUp() {
        admin = User.builder()
                .email("namse@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.toEnum("MALE"))
                .socialType(SocialType.GOOGLE)
                .build();
        admin.changeAuthorityAdmin();
        userRepository.save(admin);
    }
}
