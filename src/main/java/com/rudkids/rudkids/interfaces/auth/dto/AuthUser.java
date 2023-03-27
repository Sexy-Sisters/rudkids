package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Builder;

import java.util.UUID;

public class AuthUser {

    @Builder
        public record Login(UUID id) {
    }

    @Builder
        public record OAuth(String email, String name) {
    }
}
