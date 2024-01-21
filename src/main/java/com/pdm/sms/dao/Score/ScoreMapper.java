package com.pdm.sms.dao.Score;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dto.Course;
import com.pdm.sms.dto.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 23:00
 * @description 成绩mapper层
 **/
@Mapper
public interface ScoreMapper {
    /**
     * description: 教师用户根据专业等获取课程信息
     * param: rowBounds
     * param: condition
     * return: List<Course>
     */
    List<Course> getCourseByMap(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    /**
     * description: 管理员用户根据专业、班级查询所有分数信息
     *
     * @param: condition
     * return: List<Course>
     */
    List<Course> getCourseByAdmin(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    /**
     * description: 学生用户根据学生id获取所有分数信息
     *
     * @param: condition
     * return: List<Course>
     */
    List<Course> getCourseByStudent(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    /**
     * description: 根据学生id和课程名获取分数信息
     *
     * @param: condition
     * return: Course
     */
    Course getScoreById(@Param("condition") Map<String, Object> condition);

    /**
     * description: 教师用户获取导出成绩列表
     *
     * @param: condition
     * return: List<Course>
     */
    List<Course> getExportList(@Param("condition") Map<String, Object> condition);

    /**
     * description: 管理员用户获取导出成绩列表
     *
     * @param: condition
     * return: List<Course>
     */
    List<Course> getExportListByAdmin(@Param("condition") Map<String, Object> condition);

    /**
     * description: 学生用户获取导出成绩列表
     *
     * @param: condition
     * return: List<Course>
     */
    List<Course> getExportListByStudent(@Param("condition") Map<String, Object> condition);

    /**
     * description: 录入成绩
     *
     * @param: Score
     * return: void
     * Author: rabbiter
     * @Date: 2022/3/19 16:23
     */
    void addEntry(Score score);

    /**
     * description: 修改成绩
     *
     * @param: Score
     * return: void
     * Author: rabbiter
     * @Date: 2022/3/20 11:52
     */
    void updateEntry(Score score);

    /**
     * description: 查看是否录入成绩
     * return: Integer
     * Author: rabbiter
     *
     * @Date: 2022/3/11 15:03
     */
    Integer checkCount(@Param("condition") Map<String, Object> condition);

    /**
     * description: 根据学生id获取所有课程的绩点和学分
     *
     * @param: condition
     * return:  List<Course>
     */
    List<Course> getStudentTotal(@Param("condition") Map<String, Object> condition);
}
