package com.rudkids.core.admin.dto;

public class DeliveryTrackResponse {

    public record Info(
        State state,
        String message
    ) {
        public String getState() {
            return state.text;
        }

        public static Info getNotFoundResponse() {
            return new Info(new State(""), "");
        }
    }

    public record State(
        String text
    ) {}
}
