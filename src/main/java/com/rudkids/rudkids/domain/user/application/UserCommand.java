package com.rudkids.rudkids.domain.user.application;

import lombok.Getter;

public class UserCommand {

    @Getter
    public static class Update {
        private int age;
        private String gender;

        private Update() {
        }

        public Update(int age, String gender) {
            this.age = age;
            this.gender = gender;
        }
    }
}
