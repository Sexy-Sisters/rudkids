package com.rudkids.rudkids.domain.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ViewCount {
    private static final int INITIAL_VIEW = 0;

    @Column(name = "view")
    private long value;

    public ViewCount() {
        value = INITIAL_VIEW;
    }

    public void increase() {
        value++;
    }
}
