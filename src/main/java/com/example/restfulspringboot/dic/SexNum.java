package com.example.restfulspringboot.dic;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SexNum {
    MALE(0L, "男"), FEMALE(1L, "女");

    private Long id;
    private String sex;

    public static SexNum getSex(long id) {
        for(SexNum sexNum : SexNum.values()) {
            if(sexNum.getId() == id) {
                return sexNum;
            }
        }
        throw new RuntimeException("没有找到对应的性别，请检查参数是否正确");
    }
}
