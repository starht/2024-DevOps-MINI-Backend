package com.example.minibackend.controller;


import com.example.minibackend.error.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String code = ex.getBindingResult().getFieldError().getCode();
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ErrorResult(code, message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResult handleInternalServerError(RuntimeException ex) {
        String code = "런타임 에러";
        return new ErrorResult(code, ex.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult handleIllegalArgument(IllegalArgumentException ex) {
        String code = "사용자 중복 에러";
        return new ErrorResult(code, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception ex) {
        String code = "unknown error";
        return new ErrorResult(code, ex.getLocalizedMessage());
    }
}
