package com.rudkids.rudkids.interfaces.magazine.dto;

public class MagazineRequest {

    public record Create(String title, String content) {
    }

    public record Update(String title, String content) {
    }
}
