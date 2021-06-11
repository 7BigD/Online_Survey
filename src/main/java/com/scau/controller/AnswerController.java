package com.scau.controller;


import com.scau.entity.Answer;
import com.scau.entity.Result;
import com.scau.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Api(value = "AnswerController", tags = "问题答案接口Controller")
@Controller
@RequestMapping("//answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /*
    回答问卷。
    用户填写  paperQuestionVO 渲染的页面。
    前端封装  answer（最重要的是将paperID，questionID封装），传递给后端。
    // 已经测试成功。
     */
    @ApiOperation(value = "填写问题")
    @PostMapping("/write")
    @ResponseBody
    public Result writeAnswer(@RequestBody List<Answer> answers) {
        answerService.saveBatch(answers);
        return Result.ok().data("填写成功","ok");
    }
}

