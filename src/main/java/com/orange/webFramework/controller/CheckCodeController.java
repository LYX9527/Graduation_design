package com.orange.webFramework.controller;

import cn.dsna.util.images.ValidateCode;
import com.orange.common.AjaxResult;
import com.orange.common.Base64;
import com.orange.common.Constants;
import com.orange.common.IdUtils;
import com.orange.webFramework.utils.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/3/10 7:29 下午
 * @Version : V1.0
 */

@Api(tags = "验证码")
@RestController
public class CheckCodeController {
    @Autowired
    private RedisCache redisCache;
    /**
     * 获取验证码
     * @param response
     * @return 返回验证码与uuid
     * @throws IOException
     */

    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    @GetMapping("/checkcode")
    public AjaxResult hello(HttpServletResponse response) throws IOException {
        //设置为图像模式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //180高，40宽，5个数字，50干扰线
        ValidateCode verifyCode = new ValidateCode(180, 40, 5, 50);
        BufferedImage buffImg = verifyCode.getBuffImg();
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(buffImg, "png", os);
        String code = verifyCode.getCode();
        HashMap<String, Object> map = new HashMap<>();
        map.put("image", "data:image/png;base64," + Base64.encode(os.toByteArray()));
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        map.put("uuid", uuid);
        return AjaxResult.success("获取成功！", map);
    }
}
