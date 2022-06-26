package com.orange.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.orange.common
 * @Author : yilantingfeng
 * @Date : 2022/4/5 1:26 下午
 * @Version : V1.0
 */
public class EnclosedMap {
    public static Map Enclose(Page page){
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("data", page.getRecords());
        map.put("current", page.getCurrent());
        return map;
    }
}
