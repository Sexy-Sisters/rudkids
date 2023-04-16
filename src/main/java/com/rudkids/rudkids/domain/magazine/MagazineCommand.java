package com.rudkids.rudkids.domain.magazine;

import lombok.Builder;

public class MagazineCommand {

    @Builder
    public record Create(String title, String content) {
    }
}
