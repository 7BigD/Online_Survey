package com.scau.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.scau.entity.Paper;
import com.scau.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PapperQuestionVO {
    /**
     * 问卷ID
     */
    private Integer id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private Integer status;

    private Integer userId;

    private List<Question> questions;

    public PapperQuestionVO(Paper paper, List<Question> questions) {
        this.id = paper.getId();
        this.title = paper.getTitle();
        this.createTime = paper.getCreateTime();
        this.status = paper.getStatus();
        this.userId = paper.getUserId();
        this.questions = questions;
    }
}
