package com.pdm.sms.dao.Course;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dto.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/19 23:05
 * @description 课程信息mapper层
 **/
@Mapper
public interface CourseMapper {

    /**
     * description: 获取课程信息
     * param: rowBounds
     * param: condition
     * return: List<Course>
     * Author: rabbiter
     * @Date: 2022/3/11 15:03
     */
    List<Course> getCourseList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);
}
