package com.rudkids.rudkids.domain.product;

import lombok.Builder;

public class ProductCommand {

    public record CreateRequest(String title, String productBio) {
    }

}
