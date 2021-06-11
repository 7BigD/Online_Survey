package com.scau.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷ID，外键
     */
    private Integer paperId;

    /**
     * 问题类型： 1：单选
     * 2：多选
     * 3：简答
     */
    private Integer type;

    /**
     * 问题的标题
     */
    private String title;

    /**
     * 问题选项  1： 选择题，数组字符串  2： 简答题 空数组字符串
     */
    private String _option;

    /**
     * 是否必选题：0：非必选 1：必选
     */
    private Integer _necessary;


}
