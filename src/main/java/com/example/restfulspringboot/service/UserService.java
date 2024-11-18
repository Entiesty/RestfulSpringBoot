package com.example.restfulspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.restfulspringboot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectWithLimitAndOffset(String userName,Long sex, Long start, Long limit);
}
