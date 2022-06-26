package com.orange.webFramework.controller;

import com.orange.webFramework.bean.User;
import com.orange.webFramework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/3/9 11:06 下午
 * @Version : V1.0
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/user", params = {"id"})
    public User getById(Integer id) {
       return userService.getById(id);
    }

    @PostMapping(value = "/adduser")
    public User addUser(User user) {
        userService.save(user);
        return user;
    }
    @GetMapping(value = "/usertest", params = {"name"})
    public User getByUser(String name) {
        return userService.getUserByUsername(name);
    }
}
