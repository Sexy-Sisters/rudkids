package com.rudkids.rudkids.domain.user.exception;

public class DuplicateEmailException extends RuntimeException {
    private static final String MESSAGE = "중복된 이메일 입니다.";
    public DuplicateEmailException() {
        super(MESSAGE);
    }
}
