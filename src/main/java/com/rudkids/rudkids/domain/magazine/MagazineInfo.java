package com.rudkids.rudkids.domain.magazine;

import lombok.Builder;

public class MagazineInfo {

    @Builder
    public record Main(String title, String writer) {
    }

    @Builder
    public record Detail(
            String title,
            String writer,
            String content) {
    }
}
