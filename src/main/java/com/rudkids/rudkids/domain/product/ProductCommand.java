package com.rudkids.rudkids.domain.product;

public class ProductCommand {

    public record CreateRequest(String title, String productBio) {
    }

    public record UpdateRequest(String title, String productBio) {
    }
}
