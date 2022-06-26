package com.orange.webFramework.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Package : com.orange.spring_boot2_web.enums
 * @Author : yilantingfeng
 * @Date : 2022/3/16 12:09 下午
 * @Version : V1.0
 */
public enum EnumSex {
    MALE(1,"男"),
    FEMALE(2,"女");
    @EnumValue
    private final Integer sex;
    private final String name;
    EnumSex(Integer sex, String name) {
        this.sex = sex;
        this.name = name;
    }
}
