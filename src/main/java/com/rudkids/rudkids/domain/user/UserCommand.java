package com.rudkids.rudkids.domain.user;

import lombok.Builder;

public class UserCommand {

    @Builder
    public record Update(int age, String gender) {
    }
}
