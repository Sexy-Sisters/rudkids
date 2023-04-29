package com.rudkids.rudkids.infrastructure.magazine;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.MagazineFactory;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MagazineFactoryImpl implements MagazineFactory {

    @Override
    public Magazine create(MagazineCommand.Create command, User user) {
        var title = Title.create(command.title());
        var content = Content.create(command.content());
        return Magazine.create(user, title, content);
    }

    @Override
    public void update(MagazineCommand.Update command, Magazine magazine) {
        var title = Title.create(command.title());
        var content = Content.create(command.content());
        magazine.update(title, content);
    }
}
