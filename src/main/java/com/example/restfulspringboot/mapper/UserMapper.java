package com.example.restfulspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restfulspringboot.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM chapter11.t_user WHERE sex = #{sex} AND user_name like CONCAT('%',#{userName},'%') " +
            "LIMIT #{limit} OFFSET #{start}")
    List<User> selectWithLimitAndOffset(@Param("userName") String userName, @Param("sex") Long sex,
                                        @Param("start") Long start, @Param("limit") Long limit);
}
