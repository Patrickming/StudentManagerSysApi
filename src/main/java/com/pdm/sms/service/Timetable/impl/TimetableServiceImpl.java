package com.pdm.sms.service.Timetable.impl;

import com.pdm.sms.dao.Course.CourseInfoMapper;
import com.pdm.sms.dao.TeacherCourse.TeacherCourseMapper;
import com.pdm.sms.dao.Timetable.TimetableMapper;
import com.pdm.sms.dao.Timetable.WeekCourseMapper;
import com.pdm.sms.domain.CourseInfo;
import com.pdm.sms.domain.TeacherCourse;
import com.pdm.sms.domain.Timetable;
import com.pdm.sms.dto.WeekCourse;
import com.pdm.sms.service.Timetable.TimetableService;
import com.pdm.sms.utils.timetable.WeekUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private CourseInfoMapper courseInfoMapper;


    //其实就是把属于这个老师的课给他剥离出来
    @Override
    public List<WeekCourse> getTimetableByTeacher(Map<String, Object> condition) {
        int num = timetableMapper.checkCount(condition);
        //这里使用了getTimetable 类似封装了一下
        List<WeekCourse> weekCourseList = getTimetable(condition);

        // 获取教师负责的专业、班级、课程
        List<TeacherCourse> teacherCourseList = teacherCourseMapper.getCourseListById(condition.get("teacherId").toString());
        List<WeekCourse> newList = new ArrayList<>();
        // 将新课程的每一项设为"一"
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

    @Override
    public List<WeekCourse> getTimetable(Map<String, Object> condition) {
        int num = timetableMapper.checkCount(condition);
        List<WeekCourse> list = new ArrayList<>();
        if (num == 0) {
            for (int i = 1; i < 11; i++) {
                WeekCourse week = new WeekCourse();
                list.add(week);
            }
        } else {
            list = weekCourseMapper.getWeekCourse(condition);
            weekUtil.dealMethod(list, condition);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //出现任意异常都回滚
    public void add(List<WeekCourse> list) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("profession", list.get(0).getProfession());
        condition.put("grade", list.get(0).getGrade());
        condition.put("year", list.get(0).getYear());
        condition.put("term", list.get(0).getTerm());
        condition.put("week", list.get(0).getWeek());

        // 获取原本课程表信息
        List<Timetable> timeTableList = timetableMapper.getTimetable(condition);
        Set<Integer> ids = new HashSet<>();
        Set<Integer> weekIds = new HashSet<>();
        for (Timetable timetable : timeTableList) {
            ids.add(timetable.getId());
            weekIds.add(timetable.getWeekId());
        }
        if (ids.size() > 0) {
            // 修改
            for (int i = 0; i < list.size(); i++) {
                List<Integer> weekList = new ArrayList<>(weekIds);
                // 排序
                Collections.sort(weekList);
                WeekCourse weekCourse = list.get(i);
                weekCourse.setId(weekList.get(i));
                weekUtil.dealWeek(list.get(i));
                weekCourseMapper.update(list.get(i));
            }
        } else {
            // 新增课程表
            // 删除旧课程表
            timetableMapper.deleteTimeTable(condition);
            // 新增
            for (WeekCourse weekCourse : list) {
                weekUtil.dealWeek(weekCourse);
                weekCourseMapper.add(weekCourse);
                Timetable timetable = new Timetable();
                timetable.setWeekId(weekCourse.getId());
                timetable.setProfession(weekCourse.getProfession());
                timetable.setGrade(weekCourse.getGrade());
                timetable.setYear(weekCourse.getYear());
                timetable.setTerm(weekCourse.getTerm());
                timetable.setWeekNum(weekCourse.getWeek());
                timetableMapper.add(timetable);
            }
        }
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        courseInfoMapper.updateCourseInfo(courseInfo);
    }

}
