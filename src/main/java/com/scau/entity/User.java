package com.scau.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

//    public User(Integer str){
//        this.setId(str);
//    }

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value="用户名",name="username",example="yhs")
    private String username;

    /**
     * 用户密码（MD5）
     */
    @ApiModelProperty(value="密码",name="password",example="123456")
    private String password;

    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
