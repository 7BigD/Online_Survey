package com.scau.service.impl;

import com.scau.entity.Question;
import com.scau.mapper.QuestionMapper;
import com.scau.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
