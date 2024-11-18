package com.example.restfulspringboot.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.restfulspringboot.exception.NotFoundException;
import com.example.restfulspringboot.pojo.User;
import com.example.restfulspringboot.service.UserService;
import com.example.restfulspringboot.util.UserConverter;
import com.example.restfulspringboot.vo.ResultVo;
import com.example.restfulspringboot.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResultVo<UserVo> insertUser(@RequestBody UserVo userVo) {
        User user = UserConverter.voToPo(userVo);
        boolean result = userService.save(user);

        if (result) {
            return new ResultVo<>(true, "插入成功", UserConverter.poToVo(user));
        } else {
            return new ResultVo<>(false, "插入失败");
        }
    }

    @GetMapping("/{id}")
    public ResultVo<UserVo> getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        if (user != null) {
            UserVo vo = UserConverter.poToVo(user);
            return new ResultVo<>(true, "查询成功", vo);
        } else {
            return new ResultVo<>(false, "不存在用户【" + id + "】");
        }
    }

    @PutMapping
    public ResultVo<UserVo> updateUser(@RequestBody UserVo vo) {
        User user = UserConverter.voToPo(vo);
        boolean result = userService.updateById(user);

        if (result) {
            return new ResultVo<>(true, "更新", UserConverter.poToVo(user));
        } else {
            return new ResultVo<>(false, "更新失败");
        }
    }

    @PatchMapping("/{id}/{note}")
    public ResultVo<UserVo> changeUserNote(@PathVariable("id") Long id, @PathVariable("note") String note) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", id).set("note", note);
        boolean result = userService.update(userUpdateWrapper);

        if (result) {
            return new ResultVo<>(true, "更新", UserConverter.poToVo(userService.getById(id)));
        } else {
            return new ResultVo<>(false, "更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public ResultVo<UserVo> deleteUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        boolean result = userService.removeById(id);

        if (result) {
            return new ResultVo<>(true, "删除成功", UserConverter.poToVo(user));
        } else {
            return new ResultVo<>(false, "删除失败");
        }
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/user/index");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/exp/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVo getUserForExp(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if(user == null) {
            throw new NotFoundException(1L, "找不到用户【" + id + "】信息");
        }
        return UserConverter.poToVo(user);
    }

    @PutMapping("/header")
    public void updateUserByHeader(@RequestHeader("id") Long id,
                                   @RequestBody UserVo userVo) {
        User user = UserConverter.voToPo(userVo);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", id);

        userService.update(user, userUpdateWrapper);
    }
}
