package com.example.restfulspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.restfulspringboot.mapper.UserMapper;
import com.example.restfulspringboot.pojo.User;
import com.example.restfulspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
    private final UserMapper userMapper;
    @Override
    public List<User> selectWithLimitAndOffset(String userName,Long sex, Long start, Long limit) {
        return userMapper.selectWithLimitAndOffset(userName, sex, start, limit);
    }
}
