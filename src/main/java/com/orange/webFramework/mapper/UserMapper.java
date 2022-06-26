package com.orange.webFramework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.webFramework.bean.User;


/**
 * @Package : com.orange.spring_boot2_web.mapper
 * @Author : yilantingfeng
 * @Date : 2022/3/9 10:58 下午
 * @Version : V1.0
 */
public interface UserMapper extends BaseMapper<User> {
     User getUserByUsername(String user);

     int insertSelective(User user);



}
