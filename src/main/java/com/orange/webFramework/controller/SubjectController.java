package com.orange.webFramework.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orange.common.AjaxResult;
import com.orange.common.EnclosedMap;
import com.orange.webFramework.bean.Subject;
import com.orange.webFramework.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Package : com.orange.spring_boot2_web.controller
 * @Author : yilantingfeng
 * @Date : 2022/4/5 1:03 下午
 * @Version : V1.0
 */
@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 获取全部题目
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/allsubject")
    public AjaxResult getAllSubject(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        try {
            Page<Subject> subjectPage = new Page<>(pageNum, pageSize);
            Page<Subject> page = subjectService.page(subjectPage, null);
            Map map = EnclosedMap.Enclose(page);
            return AjaxResult.success(map);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 根据题目类型获取题目
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    @GetMapping("/subjectbyType")
    public AjaxResult getSubjectByType(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "type" ,defaultValue = "-1") Integer type) {
        Page<Subject> subjectPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        if(type != -1){
            queryWrapper.eq("type",type);
        }
        Page<Subject> page = subjectService.page(subjectPage, queryWrapper);
        Map map = EnclosedMap.Enclose(page);
        return AjaxResult.success(map);
    }

    /**
     * 根据题目技术栈类型获取题目
     * @param pageSize
     * @param pageNum
     * @param classtype
     * @return
     */
    @GetMapping("/subjectByClassType")
    public AjaxResult getSubjectByClassType(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "classtype" ,defaultValue = "-1") Integer classtype) {
        Page<Subject> subjectPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        if(classtype != -1){
            queryWrapper.eq("class_type",classtype);
        }
        Page<Subject> page = subjectService.page(subjectPage, queryWrapper);
        Map map = EnclosedMap.Enclose(page);
        return AjaxResult.success(map);
    }

    /**
     * 根据题目搜索题目
     * @param pageSize
     * @param pageNum
     * @param kw
     * @return
     */
    @GetMapping("/searchSubject")
    public AjaxResult getSearchSubject(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "kw" ,defaultValue = "") String kw){
        try{
            Page<Subject> subjectPage = new Page<>(pageNum, pageSize);
            QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("topic",kw);
            Page<Subject> page = subjectService.page(subjectPage, queryWrapper);
            Map map = EnclosedMap.Enclose(page);
            return AjaxResult.success(map);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 新增题目
     * @param subject
     * @return
     */
    @PostMapping("/addSubject")
    public AjaxResult addSubject(@RequestBody Subject subject){
        try{
            subject.setDelFlag(0);
            subjectService.save(subject);
            return AjaxResult.success(subject.getId());
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }
}
