package com.rudkids.rudkids.interfaces.magazine.dto;

import com.rudkids.rudkids.domain.magazine.MagazineCommand;
import org.springframework.stereotype.Component;

@Component
public class MagazineDtoMapper {

    public MagazineCommand.Create to(MagazineRequest.Create request) {
        return MagazineCommand.Create.builder()
                .title(request.title())
                .content(request.content())
                .build();
    }

    public MagazineCommand.Update to(MagazineRequest.Update request) {
        return MagazineCommand.Update.builder()
                .title(request.title())
                .content(request.content())
                .build();
    }
}
