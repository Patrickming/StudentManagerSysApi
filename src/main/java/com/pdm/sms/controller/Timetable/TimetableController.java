package com.pdm.sms.controller.Timetable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pdm.sms.domain.CourseInfo;
import com.pdm.sms.dto.WeekCourse;
import com.pdm.sms.service.Timetable.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 11:36
 * @description 课程表控制层
 **/
@RestController
@RequestMapping("/api/sms/timetable")
public class TimetableController {
    @Resource
    private TimetableService timetableService;

    @GetMapping("/getTimetableByTeacher")
    public List<WeekCourse> getTimetableByTeacher (@RequestParam Map<String, Object> condition) {
        return timetableService.getTimetableByTeacher(condition);
    }

    @GetMapping("/getTimetable")
    public List<WeekCourse> getStudentList (@RequestParam Map<String, Object> condition) {
        return timetableService.getTimetable(condition);
    }


    /**
     * 新增、更新课程表
     * @param WeekCourseList 课程表信息
     *
     */
    @PostMapping
    public void add(@RequestBody JSONArray weekCourseList) {
        List<WeekCourse> list = JSONObject.parseArray(weekCourseList.toJSONString(), WeekCourse.class);
        timetableService.add(list);
    }

    @PostMapping("/updateCourseInfo")
    public void updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        timetableService.updateCourseInfo(courseInfo);
    }
}

