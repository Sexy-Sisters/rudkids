package com.rudkids.rudkids.common;

import lombok.Builder;
@Builder
public record ResponseEntity<T>(T data) {
}