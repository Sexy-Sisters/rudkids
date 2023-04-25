package com.rudkids.rudkids.infrastructure.oauth.dto;

public class Birthday {

    private final Date date;

    public Birthday(Date date) {
        this.date = date;
    }

    public int getYear() {
        return date.year();
    }
}
