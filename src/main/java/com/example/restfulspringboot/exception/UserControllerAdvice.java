package com.example.restfulspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = {"com.example.restfulspringboot.controller.*"},
annotations = {Controller.class, RestController.class})
public class UserControllerAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> exception(NotFoundException ex) {
        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("code", ex.getCode());
        msgMap.put("message", ex.getCustomMsg());

        return msgMap;
    }
}
