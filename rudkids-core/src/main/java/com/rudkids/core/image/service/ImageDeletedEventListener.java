package com.rudkids.core.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageDeletedEventListener {
    private final ImageService imageService;

    @Async
    @EventListener(ImageDeletedEvent.class)
    public void handle(ImageDeletedEvent event) {
        imageService.delete(event.fileName());
    }
}
