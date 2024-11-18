package com.example.restfulspringboot.exception;

import lombok.*;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 7034896379745766939L;
    private Long code;
    private String customMsg;
}
