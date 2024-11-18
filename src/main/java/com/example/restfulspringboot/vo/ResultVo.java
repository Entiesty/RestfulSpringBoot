package com.example.restfulspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo <T>{
    private Boolean success = Boolean.FALSE;
    private String message;
    private T data;

    public ResultVo(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
