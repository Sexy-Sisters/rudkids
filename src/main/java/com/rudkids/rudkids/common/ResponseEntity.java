package com.rudkids.rudkids.common;

import lombok.Builder;

public class ResponseEntity<T> {
    private final T data;

    @Builder
    public ResponseEntity(T data) {
        this.data = data;
    }
}
