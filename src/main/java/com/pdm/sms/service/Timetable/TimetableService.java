package com.pdm.sms.service.Timetable;

import com.pdm.sms.dto.WeekCourse;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 12:02
 * @description
 **/
public interface TimetableService {

    /**
     * description: 教师用户获取课程表
     * @param: condition
     * return: List<WeekCourse>
     */
    List<WeekCourse> getTimetableByTeacher(Map<String, Object> condition);
}