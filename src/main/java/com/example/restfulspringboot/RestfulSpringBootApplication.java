package com.example.restfulspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.restfulspringboot.mapper")
public class RestfulSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulSpringBootApplication.class, args);
    }

}
