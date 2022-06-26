package com.orange.webFramework.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName topicanswer
 */
@TableName(value ="topicanswer")
@Data
public class Topicanswer implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目id
     */
    private Integer qid;

    /**
     * 正确答案id
     */
    private Integer aid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}