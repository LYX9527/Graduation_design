package com.orange.webFramework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.webFramework.bean.Subject;
import com.orange.webFramework.service.SubjectService;
import com.orange.webFramework.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

/**
* @author yilantingfeng
* @description 针对表【subject(题目表)】的数据库操作Service实现
* @createDate 2022-04-05 13:37:10
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

}




