package com.pdm.sms.dao.Timetable;

import com.pdm.sms.dto.WeekCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 14:14
 * @description 课程表具体安排
 **/
@Mapper
public interface WeekCourseMapper {
    /**
     * description: 根据专业班级获取课程表信息
     *
     * @param: condition
     * return: List<WeekCourse>
     */
    List<WeekCourse> getWeekCourse(@Param("condition") Map<String, Object> condition);
}
