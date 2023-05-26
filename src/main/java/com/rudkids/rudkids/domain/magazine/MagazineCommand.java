package com.rudkids.rudkids.domain.magazine;

import lombok.Builder;

public class MagazineCommand {

    @Builder
    public record Create(
        String title,
        String content,
        String writer
    ) {
    }

    @Builder
    public record Update(
        String title,
        String content,
        String writer
    ) {
    }
}
