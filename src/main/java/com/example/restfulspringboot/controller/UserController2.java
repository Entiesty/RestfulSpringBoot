package com.example.restfulspringboot.controller;

import com.example.restfulspringboot.pojo.User;
import com.example.restfulspringboot.service.UserService;
import com.example.restfulspringboot.util.UserConverter;
import com.example.restfulspringboot.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user2")
@RequiredArgsConstructor
public class UserController2 {
    private final UserService userService;

    @PostMapping("/entity")
    public ResponseEntity<UserVo> insertUserEntity(@RequestBody UserVo userVo) {
        User user = UserConverter.voToPo(userVo);
        boolean result = userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        String success = result ? "true" : "false";
        headers.add("success", success);

        return new ResponseEntity<>(userVo, headers, HttpStatus.CREATED);
    }

    @PostMapping("/annotation")
    public UserVo insertUserAnnotation(@RequestBody UserVo userVo) {
        User user = UserConverter.voToPo(userVo);
        boolean result = userService.save(user);

        return userVo;
    }

    @GetMapping("/{id}")
    public UserVo getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        return UserConverter.poToVo(user);
    }

    @GetMapping("/{userName}/{sex}/{start}/{limit}")
    public List<UserVo> getUsers(@PathVariable String userName, @PathVariable Long sex,
                                 @PathVariable Long start, @PathVariable Long limit) {
        return UserConverter.posToVos(userService.selectWithLimitAndOffset(userName, sex, start, limit));
    }
}
