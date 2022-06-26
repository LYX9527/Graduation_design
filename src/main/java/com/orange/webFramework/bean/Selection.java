package com.orange.webFramework.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 选项表
 * @TableName selection
 */
@TableName(value ="selection")
@Data
public class Selection implements Serializable {
    /**
     * 主键，选项id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 对应题目的ID
     */
    private Integer sid;

    /**
     * 选项内容
     */
    private String options;

    /**
     * 是否为正确答案
     */
    private Integer isRight;

    /**
     * 删除标志，0为存在，1为删除
     */
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}