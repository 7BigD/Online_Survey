package com.scau;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.entity.Answer;
import com.scau.entity.Paper;
import com.scau.entity.Question;
import com.scau.entity.User;
import com.scau.service.AnswerService;
import com.scau.service.PaperService;
import com.scau.service.QuestionService;
import com.scau.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SpringBootTest
class OnlineSurveyApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Test
    @GetMapping("/user/insert")
    public void insertUser() {
        User user = new User();
        user.setUsername("yhs");
        user.setPassword("123456");
        userService.save(user);
    }


    @Test
    public void insertPaper() {
        Paper paper = new Paper();
        paper.setTitle("学生信息收集");
        paper.setUserId(1);
        paperService.save(paper);
    }

    @Test
    public void addQuestion() {
        Question question = new Question();
        question.setPaperId(5);
        question.setType(1);
        question.setTitle("你的生日是什么");
        question.set_option("1");
        question.set_necessary(1);
        questionService.save(question);
    }

    @Test
    public void addAnswer() {
        Answer answer = new Answer();
        answer.setPaperId(5);
        answer.setQuestionId(8);
        answer.setAnswerOption("2021");
        answer.setAnswerType(1);
        answerService.save(answer);
    }


    @Test
    public void exportExcel() {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("回答情况");
        HSSFRow titleRow = sheet.createRow(0);

    }

    @Test
    public void getQuestion() {
        Integer id = 1;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("paper_id", id);
        List<Question> questions = questionService.list(queryWrapper);
        questions.forEach(System.out::println);
    }

}
