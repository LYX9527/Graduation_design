package com.orange.webFramework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orange.common.AjaxResult;
import com.orange.common.Constants;
import com.orange.common.Md5Utils;
import com.orange.webFramework.utils.redis.RedisCache;
import com.orange.webFramework.bean.RegisterBody;
import com.orange.webFramework.bean.User;
import com.orange.webFramework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/3/11 11:10 上午
 * @Version : V1.0
 */
@RestController
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 注册功能
     * @param registerBody
     * @return
     */
    @PostMapping("/register")
    public AjaxResult Register(@RequestBody RegisterBody registerBody) {
        // 校验验证码
        String verifyKey = Constants.CAPTCHA_CODE_KEY + registerBody.getUuid();
        String TempCode = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (registerBody.getCode() != null && TempCode != null) {
            if (registerBody.getCode().equalsIgnoreCase(TempCode)) {
                if (userService.getOne(new QueryWrapper<User>().eq("user", registerBody.getUser())) != null) {
                    return AjaxResult.error("用户名重复!!");
                } else {
                    String password = Md5Utils.getMD5Str(registerBody.getPassword());
                    User user1 = new User(null,
                            registerBody.getUser(),
                            registerBody.getName(),
                            password,
                            registerBody.getEmail(),
                            registerBody.getPhone());
                    boolean isAddStatus = userService.save(user1);
                    if (isAddStatus) {
                        return AjaxResult.success("注册成功！");
                    } else {
                        return AjaxResult.error();
                    }
                }
            } else {
                return AjaxResult.error("验证码错误！！");
            }
        } else {
            return AjaxResult.error("验证码已失效！");
        }
    }
}
