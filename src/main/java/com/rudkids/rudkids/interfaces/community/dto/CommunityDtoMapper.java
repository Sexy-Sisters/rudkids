package com.rudkids.rudkids.interfaces.community.dto;

import com.rudkids.rudkids.domain.community.CommunityCommand;
import org.springframework.stereotype.Component;

@Component
public class CommunityDtoMapper {

    public CommunityCommand.Create toCommand(CommunityRequest.Create request) {
        return CommunityCommand.Create.builder()
            .title(request.title())
            .content(request.content())
            .type(request.type())
            .image(request.image())
            .build();
    }

    public CommunityCommand.Update toCommand(CommunityRequest.Update request) {
        return CommunityCommand.Update.builder()
            .title(request.title())
            .content(request.content())
            .image(request.image())
            .build();
    }
}
