package com.rudkids.core.video.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class VideoNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 비디오입니다.";

    public VideoNotFoundException() {
        super(MESSAGE);
    }
}
