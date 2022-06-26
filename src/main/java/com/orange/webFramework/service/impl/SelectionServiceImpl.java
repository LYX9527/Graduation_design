package com.orange.webFramework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.webFramework.bean.Selection;
import com.orange.webFramework.mapper.SelectionMapper;
import com.orange.webFramework.service.SelectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yilantingfeng
 * @description 针对表【selection(选项表)】的数据库操作Service实现
 * @createDate 2022-04-05 23:21:04
 */
@Service
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection>
        implements SelectionService {

    @Resource
    private SelectionMapper selectionMapper;

    @Override
    public List getBySid(Integer sid) {
        QueryWrapper<Selection> wrapper = new QueryWrapper<>();
        wrapper.eq("sid", sid);
        return selectionMapper.selectList(wrapper);
    }

    @Override
    public Selection getRight(String topicId) {
        QueryWrapper<Selection> wrapper = new QueryWrapper<>();
        wrapper.eq("sid", topicId).eq("is_right", 1);
        return selectionMapper.selectOne(wrapper);
    }
}




