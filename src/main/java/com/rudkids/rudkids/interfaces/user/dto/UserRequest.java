package com.rudkids.rudkids.interfaces.user.dto;

import lombok.Getter;

public class UserRequest {

    @Getter
    public static class SignUp {
        private int age;

        private String gender;

        private SignUp() {
        }

        public SignUp(int age, String gender) {
            this.age = age;
            this.gender = gender;
        }
    }

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
