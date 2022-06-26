package com.orange.webFramework.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 题目表
 * @TableName subject
 */
@TableName(value ="subject")
@Data
public class Subject implements Serializable {
    /**
     * 主键，题目id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目
     */
    private String topic;

    /**
     * 题目类别，0为选择题，1为填空题，2为简答题
     */
    private Integer type;

    /**
     * 题目技术栈类型 ，0为java ，1为javascript 2为c 3为其他
     */
    private Integer classType;

    /**
     * 删除标志
     */
    @TableField(select = false)
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}