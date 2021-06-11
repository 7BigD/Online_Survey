package com.scau.mapper;

import com.scau.entity.Paper;
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
public interface PaperMapper extends BaseMapper<Paper> {

//    @Select("SELECT * FROM paper,user WHERE　paper.user_id = user.id")
//    public List<Paper> getPapers(Integer id);
}
