package com.orange.webFramework;

import com.orange.webFramework.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot2WebApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }


    @Test
    void testuser(){
//        User user = userMapper.selectById(10);
//        System.out.println(user);
    }
}
