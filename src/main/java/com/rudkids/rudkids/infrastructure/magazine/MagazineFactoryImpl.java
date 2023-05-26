package com.rudkids.rudkids.infrastructure.magazine;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import com.rudkids.rudkids.domain.magazine.MagazineFactory;
import com.rudkids.rudkids.domain.magazine.domain.Content;
import com.rudkids.rudkids.domain.magazine.domain.Magazine;
import com.rudkids.rudkids.domain.magazine.domain.Title;
import com.rudkids.rudkids.domain.magazine.domain.Writer;
import org.springframework.stereotype.Component;

@Component
public class MagazineFactoryImpl implements MagazineFactory {

    @Override
    public Magazine create(MagazineCommand.Create command) {
        var title = Title.create(command.title());
        var content = Content.create(command.content());
        var writer = Writer.create(command.writer());
        return Magazine.create(title, content, writer);
    }

    @Override
    public void update(MagazineCommand.Update command, Magazine magazine) {
        var title = Title.create(command.title());
        var content = Content.create(command.content());
        var writer = Writer.create(command.writer());
        magazine.update(title, content, writer);
    }
}
