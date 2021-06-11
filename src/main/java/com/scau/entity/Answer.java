package com.scau.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "User对象", description = "用户对象User")
@Data
@EqualsAndHashCode(callSuper = false)
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 答案id
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷id
     */

    private Integer paperId;

    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 问题类型
     * 1：单选
     * 2：多选
     * 3：简答
     */
    @ApiModelProperty( example = "1")
    private Integer answerType;

    /**
     * 提交问卷时间、答题时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 答题选项
     * 1：选择题，来自question表的问题选项，单选题只有一个option，多选至少一个
     * [option1,option2,option3...]
     * 2：简答题，至多一个元素的数组字符串
     * ["只能有一个元素"]
     * 若未达，则无元素
     * []
     */
    private String answerOption;


}
