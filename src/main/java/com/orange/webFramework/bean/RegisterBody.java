package com.orange.webFramework.bean;


import lombok.Data;

/**
 * @Package : com.orange.spring_boot2_web.bean
 * @Author : yilantingfeng
 * @Date : 2022/3/13 4:53 下午
 * @Version : V1.0
 */
@Data
public class RegisterBody {
    private String user;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String code;
    private String uuid;
}
