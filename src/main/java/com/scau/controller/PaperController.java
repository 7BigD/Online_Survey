package com.scau.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.entity.Answer;
import com.scau.entity.Paper;
import com.scau.entity.Question;
import com.scau.entity.Result;
import com.scau.service.AnswerService;
import com.scau.service.PaperService;
import com.scau.service.QuestionService;
import com.scau.vo.PapperQuestionVO;
import com.scau.vo.QuestionAnswerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Api(value = "PaperController", tags = "问卷接口Controller")
@Controller
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;


    @ApiOperation(value = "添加问卷（别理这个，之前写的另一个思路）")
    // 添加问卷
    @GetMapping("/add")
    @ResponseBody
    public Result addPaper(@RequestBody @ApiParam(name = "papperQuestionVO", value = "传入PapperQuestionVO json格式", required = true)  PapperQuestionVO papperQuestionVO) {
        System.out.println(papperQuestionVO.toString());

        List<Question> questions = papperQuestionVO.getQuestions();
        Paper paper = new Paper();
//        paper.setId(papperQuestionVO.getId());//后台添加主键生成插件。
        paper.setTitle(papperQuestionVO.getTitle());
        paper.setCreateTime(papperQuestionVO.getCreateTime());
        paper.setStatus(papperQuestionVO.getStatus());
        paper.setUserId(papperQuestionVO.getUserId());
        boolean b = paperService.save(paper);
        //遍历question。
        questionService.saveBatch(questions);
        if (b) {
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "新建问卷")
    @PostMapping("/insertPaper")
    @ResponseBody
    public Result insertPaper(@RequestBody @ApiParam(name = "paper", value = "传入paper json格式") Paper paper) {
        boolean res = paperService.save(paper);
        if (res) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "删除问卷信息")
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result deletePaper(@PathVariable(name = "id") @ApiParam(name = "id", value = "传入paper ID", required = true) Integer id) {
        /*
        1. 删除 paper、question、answer。
            删除paper。用 paperId
            删除question。
            删除answer。
         */
        // 删除 paper
        QueryWrapper qr = new QueryWrapper();
        qr.eq("id", id);
        boolean b = paperService.remove(qr);
        QueryWrapper qr2 = new QueryWrapper();
        qr2.eq("paper_id", id);
        boolean b2 = questionService.remove(qr2);
        boolean b1 = answerService.remove(qr2);
        if (b) return Result.ok();
        else return Result.error();
    }


    /*
    查到某个用户 ID 下的所有 paper。
     */
    @ApiOperation(value = "获取当前用户(ID)下的所有paper", notes = "分页查询")
    @GetMapping("/getPapers/{user_id}")
    @ResponseBody
    public Result getPapers(@PathVariable(name = "user_id") @ApiParam(name = "user_id", value = "传入user ID", required = true) Integer user_id) {
        QueryWrapper qr = new QueryWrapper();
        qr.eq("user_id", user_id);
        List<Paper> papers = paperService.list(qr);

//        Page<Paper> paperPage = new Page<>(1,5);
//        paperService.page(paperPage, qr);
//        List<Paper> records = paperPage.getRecords();
//        records.forEach(System.out::println);
        return Result.ok().data("用户问卷", papers);
        // 算了不写分页查询了。
    }

    /*
    根据PaperId查问卷。
     */
    @ApiOperation(value = "根据PaperId得到问卷", notes = "修改paper 第一步，找到这个paper")
    @GetMapping("/getOnePaper/{paperId}")
    @ResponseBody
    public Paper getOnePaper(@PathVariable(name = "paperId") Integer paperId) {
        Paper paper = paperService.getById(paperId);
        return paper;
    }

    /*
    修改paper的标题或其他内容。
     */
    @ApiOperation(value = "修改paper的标题",notes = "传入paper id 和 paper title给后端")
    @PostMapping("/updateOnePaper")
    @ResponseBody
    public Paper getOnePaper(@ApiParam(name = "id", value = "问卷的id", required = true) Integer id, @ApiParam(name = "title", value = "问卷的标题", required = true) String title) {
        Paper paper = paperService.getById(id);
        paper.setTitle(title);
        paperService.updateById(paper);
        return paper;
    }

    /*
    点击发布按钮，传入paperID
    返回发布后的url 连接 不安全。
    存在的bug： 每个人都可以访问。
     */
    @ApiOperation(value = "发布paper")
    @GetMapping("/publish/{id}")
    @ResponseBody
    public Result getPublishPaper(@PathVariable(name = "id") Integer id) {
        Paper paper = paperService.getById(id);
        paper.setStatus(1);
        boolean b = paperService.updateById(paper);
        return Result.ok().data("url", "/publishedPaper/" + id);
    }

    /*
    json 格式。
     */
    @ApiOperation(value = "获取已经发布的paper 让别人填问卷， 返回PapperQuestionVO")
    @GetMapping("/publishedPaper/{id}")
    @ResponseBody
    public Result getPublishedPaper(@PathVariable(name = "id") Integer id) {
        Paper paper = paperService.getById(id);
        if (paper.getStatus() == 1) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("paper_id", id);
            List<Question> questions = questionService.list(queryWrapper);
            PapperQuestionVO papperQuestionVO = new PapperQuestionVO(paper, questions);
            return Result.ok().data("PapperQuestionVO", papperQuestionVO);
        } else {
            return Result.error().data("PapperQuestionVO", null);
        }
    }

    @ApiOperation(value = "取消发布")
    @GetMapping("/notPublishedPaper/{id}")
    @ResponseBody
    public Result notPublishedPaper(@PathVariable("id") Integer id) {
        Paper paper = paperService.getById(id);
        paper.setStatus(0);
        boolean b = paperService.updateById(paper);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }


    /*
    导出数据。
    根据paperID
     */
    @ApiOperation("导出数据")
    @GetMapping("/export/{id}")
    @ResponseBody
    public Result export(@PathVariable("id") Integer id) {
//        Paper paper = paperService.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paper_id", id);
        List<Question> questions = questionService.list(queryWrapper);
        List<QuestionAnswerVO> questionAnswerVOS = new ArrayList<>();
        for (Question question : questions) {
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("question_id", question.getId());
            List<Answer> answers = answerService.list(queryWrapper2);
            QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO(question, answers);
            questionAnswerVOS.add(questionAnswerVO);
        }
        return Result.ok().data("questionAnswerVOS", questionAnswerVOS);
    }


    /*
    导出为excel
     */
    @GetMapping("/exportExcel/{id}")
    @ResponseBody
    public Result exportExcel(@PathVariable("id") Integer id) throws IOException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paper_id", id);
        List<Question> questions = questionService.list(queryWrapper);
        List<QuestionAnswerVO> questionAnswerVOS = new ArrayList<>();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("回答情况");
        HSSFRow titleRow = sheet.createRow(0);
        int maxlength = 0;

        for (int i = 0;  i < questions.size(); i++) {
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(questions.get(i).getTitle());
            //  搞到 首行标题, 并在首行写入数据。
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("question_id", questions.get(i).getId());
            List<Answer> answers = answerService.list(queryWrapper2);   // 这里效率好低啊



            maxlength = Math.max(maxlength, answers.size());  // 得到maxlength

            QuestionAnswerVO questionAnswerVO = new QuestionAnswerVO(questions.get(i), answers);
            questionAnswerVOS.add(questionAnswerVO);
            // 搞到 一个 questionID 对应的 一系列 answer。
        }

        for (int i = 0; i < maxlength; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < questions.size(); j++) {
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("question_id", questions.get(j).getId());
                List<Answer> answers = answerService.list(queryWrapper3);   // 这里效率好低啊
                row.createCell(j).setCellValue(answers.get(i).getAnswerOption());
            }
        }
        hssfWorkbook.write(new FileOutputStream("/Users/Desktop/data.xls"));
        return Result.ok();
    }

}

