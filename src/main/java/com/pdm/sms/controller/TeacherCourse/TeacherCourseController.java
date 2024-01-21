package com.pdm.sms.controller.TeacherCourse;

import com.pdm.sms.service.TeacherCourse.TeacherCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 9:42
 * @description 教师课程表控制层
 **/
@RestController
@RequestMapping("/api/sms/teacher/course")
public class TeacherCourseController {

    @Resource
    private TeacherCourseService teacherCourseService;

    //获取了下拉框的所有信息
    @GetMapping("/getProfessionInfoByAdmin")
    public List<Map<String, Object>> getProfessionInfoByAdmin() {
        return teacherCourseService.getProfessionInfoByAdmin();
    }

    @GetMapping("/getProfessionInfoByTeacher/{teacherId}")
    public List<Map<String, Object>> getProfessionInfoByTeacher(@PathVariable("teacherId") String teacherId) {
        return teacherCourseService.getProfessionInfoByTeacher(teacherId);
    }

}

