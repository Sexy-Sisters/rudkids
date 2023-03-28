package com.rudkids.rudkids.common;

import lombok.Builder;

public record ResponseEntity<T>(T data) {
    @Builder
    public ResponseEntity {
    }
}