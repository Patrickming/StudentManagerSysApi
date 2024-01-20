package com.pdm.sms.service.Timetable.impl;

import com.pdm.sms.dao.TeacherCourse.TeacherCourseMapper;
import com.pdm.sms.dao.Timetable.TimetableMapper;
import com.pdm.sms.dao.Timetable.WeekCourseMapper;
import com.pdm.sms.domain.TeacherCourse;
import com.pdm.sms.dto.WeekCourse;
import com.pdm.sms.service.Timetable.TimetableService;
import com.pdm.sms.utils.timetable.WeekUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 12:03
 * @description 课程表业务层实现类
 **/
@Service
public class TimetableServiceImpl implements TimetableService {

    @Resource
    private TimetableMapper timetableMapper;
    @Resource
    private WeekCourseMapper weekCourseMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Resource
    private WeekUtil weekUtil;


    //其实就是把属于这个老师的课给他剥离出来
    @Override
    public List<WeekCourse> getTimetableByTeacher(Map<String, Object> condition) {
        int num = timetableMapper.checkCount(condition);
        List<WeekCourse> weekCourseList = new ArrayList<>();
        if (num == 0) {
            // 空课程表 一天10节课 所以是10条week数据
            for (int i = 1; i < 11; i++) {
                WeekCourse week = new WeekCourse();
                weekCourseList.add(week);
            }
        } else {
            weekCourseList = weekCourseMapper.getWeekCourse(condition);
            weekUtil.dealMethod(weekCourseList, condition);
        }

        // 获取教师负责的专业、班级、课程
        List<TeacherCourse> teacherCourseList = teacherCourseMapper.getCourseListById(condition.get("teacherId").toString());
        List<WeekCourse> newList = new ArrayList<>();
        // 将新课程的每一项设为"——"
        for (int i = 1; i < 11; i++) {
            WeekCourse week = new WeekCourse();
            weekUtil.dealWeek(week);
            newList.add(week);
        }
        if (num == 0) {
            return newList;
        }
        for (TeacherCourse teacherCourse : teacherCourseList) {

            for (int i = 0; i < weekCourseList.size(); i++) {
                WeekCourse weekCourse = weekUtil.dealWeekCourse(newList.get(i), weekCourseList.get(i), teacherCourse.getName());
                newList.set(i, weekCourse);
            }
        }

        return newList;

    }


}
