package com.pdm.sms.dao.Course;

import com.pdm.sms.domain.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 0:39
 * @description 课程具体安排表
 **/
@Mapper
public interface CourseInfoMapper {
    /**
     * description: 新增课程具体安排（周数等）
     *
     * @param: courseInfo
     * return: void
     */
    void addCourseInfo(CourseInfo courseInfo);

    /**
     * description: 修改课程具体安排（周数等）
     *
     * @param: courseInfo
     * return: void
     */
    void updateCourseInfo(CourseInfo courseInfo);

    /**
     * description: 删除课程具体安排
     *
     * @param: String
     * return: void
     */
    void deleteInfo(@Param("id") String id);

    /**
     * description: 删除 student_course 关联的课程数据
     *
     * @param: String
     * return: void
     */
    void deleteCourseStu(@Param("id") String id);

    /**
     * description: 删除 teacher_course 关联的课程数据
     *
     * @param: String
     * return: void
     */
    void deleteCourseTec(@Param("id") String id);

    /**
     * description: 获取课程信息详情
     *
     * @param: Map<String, Object> condition
     * return: CourseInfo
     */
    CourseInfo getInfo(@Param("condition") Map<String, Object> condition);
}
