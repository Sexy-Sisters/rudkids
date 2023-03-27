package com.rudkids.rudkids.global.error;

import com.rudkids.rudkids.global.error.dto.ErrorResponse;
import com.rudkids.rudkids.global.error.exception.BadRequestException;
import com.rudkids.rudkids.global.error.exception.InternalException;
import com.rudkids.rudkids.global.error.exception.NotFoundException;
import com.rudkids.rudkids.global.error.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    private static final String ERROR_MESSAGE_FORM = "에러 메세지 : ";
    private static final String UNKNOWN_PROBLEM = "알 수 없는 에러가 발생하였습니다.";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        log.error(ERROR_MESSAGE_FORM + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error(ERROR_MESSAGE_FORM + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
        log.error(ERROR_MESSAGE_FORM + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorResponse> handleInternalException(InternalException e) {
        log.error(ERROR_MESSAGE_FORM + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse(UNKNOWN_PROBLEM));
    }
}
