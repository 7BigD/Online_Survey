package com.scau.entity;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 问卷创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 问卷发布状态
     */
    private Integer status;

    /**
     * 问卷创建人ID
     */
    private Integer userId;


}
