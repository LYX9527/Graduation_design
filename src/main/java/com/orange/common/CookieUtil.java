package com.orange.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Package : com.orange.common
 * @Author : yilantingfeng
 * @Date : 2022/3/12 6:14 下午
 * @Version : V1.0
 */
public class CookieUtil {

    //	获取Cookie的方法
    public static String getCookie(HttpServletRequest request, String cookieid) {
        Cookie[] cookies = request.getCookies();
        String login_number = "";
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(cookieid.equals(name)){
                    login_number = cookie.getValue();
                }
            }
        }
        return login_number;

    }

    //	设置Cookie值的方法
    public static void setCookieValue(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String cookieName,
                                      String cookieValue,
                                      Integer maxAge){
        //TODO	对Cookie进行加密封装
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge); // Cookie的存活时间（自定义）
        cookie.setPath("/"); // 默认路径
        response.addCookie(cookie);
    }

    //	设置Cookie编码（设置Cookie的话，直接调用这个方法即可，内部会去调用设置值的方法）
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String cookieName,
                                 String cookieValue,
                                 Integer maxAge){
        try {
            cookieValue = URLEncoder.encode(cookieValue,"utf-8");
            setCookieValue(request,response,cookieName,cookieValue,maxAge);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //	删除Cookie
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String cookieName) {
        try {
            String deleteValue = URLEncoder.encode("", "utf-8");
            setCookieValue(request, response, cookieName, deleteValue, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}


