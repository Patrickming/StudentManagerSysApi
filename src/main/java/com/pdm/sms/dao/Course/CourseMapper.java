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
     *
     * @Date: 2022/3/11 15:03
     */
    List<Course> getCourseList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    /**
     * description: 新增课程信息
     *
     * @param course
     * @return void
     */
    void addCourse(Course course);

    /**
     * description: 删除课程信息
     *
     * @param ids
     * @return void
     */
    void delete(@Param("ids") List<Long> ids);

    /**
     * description: 修改课程信息
     *
     * @param course
     * @return void
     */
    void update(Course course);

    /**
     * description: 查看课程最大id
     * return: Integer
     */
    String checkCodeCount(@Param("condition") Map<String, Object> condition);

    /**
     * description: 根据专业、学期获取课程列表
     * @param: condition
     * return:  List<Course>
     */
    List<Course> getCourseByMap(@Param("condition") Map<String, Object> condition);
}
