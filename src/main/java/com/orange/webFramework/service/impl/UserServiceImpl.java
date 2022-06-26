package com.orange.webFramework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.webFramework.bean.User;
import com.orange.webFramework.mapper.UserMapper;
import com.orange.webFramework.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Package : com.orange.spring_boot2_web.service.impl
 * @Author : yilantingfeng
 * @Date : 2022/3/10 9:04 上午
 * @Version : V1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User getUserByUsername(String user) {
        return userMapper.getUserByUsername(user);
    }
}
