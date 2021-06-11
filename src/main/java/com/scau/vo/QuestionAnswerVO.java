package com.scau.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.scau.entity.Answer;
import com.scau.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerVO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer paperId;

    private Integer type;

    private String title;

    private String _option;

    private Integer _necessary;

    private List<Answer> answers;

    public QuestionAnswerVO(Question question, List answers) {
        this.id = question.getId();
        this.paperId = question.getPaperId();
        this.type = question.getType();
        this.title = question.getTitle();
        this._option = question.get_option();
        this._necessary = question.get_necessary();
        this.answers = answers;
    }
}
