package com.rudkids.core.schedule.dto;

public class DeliveryTrackResponse {

    public record Info(
        State state
    ) {
        public String getState() {
            return state.text;
        }
    }

    public record State(
        String text
    ) {}
}
