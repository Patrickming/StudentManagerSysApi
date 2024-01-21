package com.pdm.sms.dao.TeacherCourse;

import com.pdm.sms.domain.TeacherCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 9:46
 * @description 教师课程Mapper层
 **/
@Mapper
public interface TeacherCourseMapper {
    /**
     * description: 根据教师id获取教师课程信息
     * @param: String
     * return: List<TeacherCourse>
     */
    List<TeacherCourse> getCourseListById(@Param("id") String id);



    /**
     * description: 获取教师id和专业获取班级、课程名
     * @param:
     * return: List<TeacherCourse>
     */
    List<TeacherCourse> getGradeInfoByMap(@Param("condition") Map<String, Object> condition);
}
