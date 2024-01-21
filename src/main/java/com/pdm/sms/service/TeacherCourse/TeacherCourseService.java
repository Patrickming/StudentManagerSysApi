package com.pdm.sms.service.TeacherCourse;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 9:43
 * @description 教师课程表Service层
 **/
public interface TeacherCourseService {

    /**
     * description: 管理员用户获取所有的班级和课程，渲染成下拉框选择
     * return: List<Map<String, Object>>
     */
    List<Map<String, Object>> getProfessionInfoByAdmin();

    /**
     * description: 根据教师id获取教师用户所管理的专业、班级、课程名等信息
     * @param: String
     * return: List<Map<String, Object>>
     */
    List<Map<String, Object>> getProfessionInfoByTeacher(String teacherId);
}
