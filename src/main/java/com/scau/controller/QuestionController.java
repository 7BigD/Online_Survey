package com.scau.controller;


import com.scau.entity.Question;
import com.scau.entity.Result;
import com.scau.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Api(value = "QuestionController", tags = "问题接口Controller")
@Controller
@RequestMapping("//question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    // 添加问题。  前端的页面跳转的时候
    // （也就是 从 paper界面，路由跳转的时候 传递了 paperID）
    // 前端 直接封装question （里面不包含questionID），但questionID后端生成，
    //  如果不跳转 使用 ajax 局部跳转
    @ApiOperation(value = "添加问题")
    @PostMapping("/add")
    @ResponseBody
    public Result addQuestion(@RequestBody @ApiParam(name = "问题对象", value = "传入json格式", required = true) Question question) {
        //问题的id，自增。
        boolean b = questionService.save(question);
        if (b) {
            return Result.ok().data("isAdd", "success");
        } else {
            return Result.error().data("isAdd", "error");
        }
    }


    /*
    根据questionId
     */

    // 编辑问题
    // 前端传入   当前问题ID
    // 后端  找到 当前问题 ，并 返回 Question  给前端。 以json格式
    //  前端 填入/修改 新的Question信息，提交给后端。更新数据库
    @ApiOperation(value = "返回question对象")
    @GetMapping("/find/{quesionID}")
    @ResponseBody
    public Result findQuestion(@PathVariable("quesionID") Integer quesionID) {
        Question q = questionService.getById(quesionID);
        return Result.ok().data("question", q);
    }

    @ApiOperation(value = "传入question对象，更新")
    @PutMapping("/update")
    public boolean updateQuestion(@RequestBody @ApiParam(name = "问题对象", value = "传入json格式", required = true) Question question) {
        return questionService.updateById(question);
    }



}