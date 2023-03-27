package com.rudkids.rudkids.domain.user.application;

import lombok.Builder;
import lombok.Getter;

public class UserCommand {

    @Getter
    @Builder
        public record Update(int age, String gender) {
    }
}
