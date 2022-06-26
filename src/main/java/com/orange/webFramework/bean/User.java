package com.orange.webFramework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : com.orange.spring_boot2_web.bean
 * @Author : yilantingfeng
 * @Date : 2022/3/9 10:56 下午
 * @Version : V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String user;
    private String name;
    private String password;
    private String email;
    private String phone;
}
