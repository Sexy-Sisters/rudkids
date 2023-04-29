package com.rudkids.rudkids.domain.magazine;

import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.user.domain.User;

public interface MagazineFactory {
    Magazine create(MagazineCommand.Create command, User user);
    void update(MagazineCommand.Update command, Magazine magazine);
}
