package com.orange.webFramework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.webFramework.bean.User;

/**
 * @Package : com.orange.spring_boot2_web.service
 * @Author : yilantingfeng
 * @Date : 2022/3/10 9:21 上午
 * @Version : V1.0
 */
public interface UserService  extends IService<User>{
     User getUserByUsername(String user);

}
