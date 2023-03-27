package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class AuthUser {

    @Getter
    @Builder
        public record Login(UUID id) {
    }

    @Getter
    @Builder
        public record OAuth(String email, String name) {
    }
}
