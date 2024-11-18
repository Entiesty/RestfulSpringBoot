package com.example.restfulspringboot.vo;

import com.example.restfulspringboot.dic.SexNum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Long id;
    private String userName;
    private SexNum sex;
    private String note;
}
