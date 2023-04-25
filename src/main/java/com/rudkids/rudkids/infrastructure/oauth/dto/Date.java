package com.rudkids.rudkids.infrastructure.oauth.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private int year;

    public Date(int year) {
        this.year = year;
    }

    public int year() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        int nowYear = Integer.parseInt(now.format(formatter));
        return (nowYear - year) + 1;
    }
}
