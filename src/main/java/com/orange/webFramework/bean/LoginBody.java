package com.orange.webFramework.bean;

import lombok.Data;

/**
 * @Package : com.orange.spring_boot2_web.bean
 * @Author : yilantingfeng
 * @Date : 2022/3/13 4:49 下午
 * @Version : V1.0
 */
@Data
public class LoginBody {
    private String user;
    private String password;
    private String code;
    private String uuid;
}
