package com.example.restfulspringboot.util;

import com.example.restfulspringboot.dic.SexNum;
import com.example.restfulspringboot.pojo.User;
import com.example.restfulspringboot.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User voToPo(UserVo vo) {
        User po = new User();
        po.setId(vo.getId());
        po.setUserName(vo.getUserName());
        po.setNote(vo.getNote());
        po.setSexId(vo.getSex().getId());

        return po;
    }

    public static UserVo poToVo(User po) {
        UserVo vo = new UserVo();
        vo.setId(po.getId());
        vo.setUserName(po.getUserName());

        if (po.getSexId() != null) {
            vo.setSex(SexNum.getSex(po.getSexId()));
        } else {
            vo.setSex(null); // 或者设置一个默认值，比如 MALE 或 FEMALE
        }

        vo.setNote(po.getNote());
        return vo;
    }


    public static List<UserVo> posToVos(List<User> pos) {
        List<UserVo> vos = new ArrayList<>();
        for(User po : pos) {
            vos.add(poToVo(po));
        }

        return vos;
    }
}
