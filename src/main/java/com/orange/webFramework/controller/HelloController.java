package com.orange.webFramework.controller;

import com.orange.common.Md5Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/3/9 7:31 下午
 * @Version : V1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam("password") String pwd){
        String md5Str = Md5Utils.getMD5Str("0000000");
        return md5Str;
    }
    @GetMapping(value = "/getuser",params = {"user","password"})
    public Map<String,String> getUser(@RequestParam("user") String user,@RequestParam("password") String pwd){
        HashMap<String, String> map = new HashMap<>();
        map.put("user", user);
        map.put("password", pwd);
        return map;
    }
}
