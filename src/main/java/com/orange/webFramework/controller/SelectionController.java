package com.orange.webFramework.controller;

import com.orange.common.AjaxResult;
import com.orange.webFramework.bean.Selection;
import com.orange.webFramework.bean.SelectionBody;
import com.orange.webFramework.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.orange.webFramework.controller
 * @Author : yilantingfeng
 * @Date : 2022/4/5 5:40 PM
 * @Version : V1.0
 */
@RestController
public class SelectionController {
    @Autowired
    private SelectionService selectionService;

    /**
     * 根据题目ID获取选项
     * @param sid
     * @return
     */
    @GetMapping("/selectionBySid")
    public AjaxResult getSelection(@RequestParam(value = "sid" ,defaultValue = "-1") Integer sid) {
        try {
            List selectionList = selectionService.getBySid(sid);
            return AjaxResult.success(selectionList);
        }catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 根据题目id获取正确选项
     * @param topicId
     * @return
     */
    @GetMapping("/getRightSelection")
    public AjaxResult getRightSelection(String topicId){
        try {
            Selection right = selectionService.getRight(topicId);
            return AjaxResult.success(right);
        }catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 添加选项
     * @param selectionBody
     * @return
     */
    @PostMapping("/addSelection")
    public AjaxResult addSelection(@RequestBody SelectionBody selectionBody) {
        try {
            ArrayList<Number> list = new ArrayList<>();
            for (Selection s : selectionBody.getSelectionList()) {
                s.setDelFlag(0);
                selectionService.save(s);
                list.add(s.getId());
            }
            return AjaxResult.success(list);
        }catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
