package com.orange.webFramework.bean;

import lombok.Data;

import java.util.List;

/**
 * @Package : com.orange.webFramework.bean
 * @Author : yilantingfeng
 * @Date : 2022/4/5 11:53 PM
 * @Version : V1.0
 */
@Data
public class SelectionBody {
    private List<Selection> selectionList;
}
