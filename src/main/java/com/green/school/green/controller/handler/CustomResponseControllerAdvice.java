package com.green.school.green.controller.handler;

import com.green.school.green.dto.response.ParseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomResponseControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ParseResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String message = "invalid request params";
        String details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .reduce("", (a, b) -> a + ", " + b);

        ParseResponse response = new ParseResponse(HttpStatus.BAD_REQUEST.value(), 400, message+details,null,null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpCode()));
    }
}
