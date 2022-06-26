package com.orange.webFramework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orange.common.*;
import com.orange.webFramework.utils.redis.RedisCache;
import com.orange.webFramework.bean.LoginBody;
import com.orange.webFramework.bean.User;
import com.orange.webFramework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/3/10 8:17 下午
 * @Version : V1.0
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    private RedisCache redisCache;
    @PostMapping("/login")
    public AjaxResult Login(@RequestBody LoginBody loginbody,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + loginbody.getUuid();
        String TempCode = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (loginbody.getCode() != null && TempCode != null) {
            if (loginbody.getCode().equalsIgnoreCase(TempCode)) {
                User byUser = userService.getOne(new QueryWrapper<User>().eq("user",loginbody.getUser()));
                if (byUser == null) {
                    return AjaxResult.error("用户不存在");
                } else {
                    if (byUser.getPassword().equals(Md5Utils.getMD5Str(loginbody.getPassword()))) {
                        request.getServletContext().setAttribute("code", null);
                        String token = IdUtils.fastUUID();
                        CookieUtil.setCookieValue(request,response,"User-Token",token,60*15);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("info", byUser);
                        map.put("token", token);
                        return AjaxResult.success("登陆成功",map);
                    } else {
                        return AjaxResult.error("密码错误");
                    }
                }
            }else{
                return AjaxResult.error("验证码错误");
            }
        } else {
            return AjaxResult.error("验证码错误！！", new HashMap<>().put("code", 0));
        }
    }
}
