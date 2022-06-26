package com.orange.webFramework.service;

import com.orange.webFramework.bean.Selection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yilantingfeng
* @description 针对表【selection(选项表)】的数据库操作Service
* @createDate 2022-04-05 23:21:04
*/
public interface SelectionService extends IService<Selection> {

    List getBySid(Integer sid);

    Selection getRight(String topicId);
}
