package com.rudkids.rudkids.domain.product.exception;

public class InvalidBioException extends RuntimeException {
    private static final String MESSAGE = "잘못된 소개글 형식입니다.";

    public InvalidBioException() {
        super(MESSAGE);
    }
}
