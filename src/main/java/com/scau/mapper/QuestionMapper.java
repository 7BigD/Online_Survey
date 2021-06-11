package com.scau.mapper;

import com.scau.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
public interface QuestionMapper extends BaseMapper<Question> {

//    @Select("SELECT * FROM question where id = #{id}")
//    public List<Question> getQuestions(Integer id);

}
