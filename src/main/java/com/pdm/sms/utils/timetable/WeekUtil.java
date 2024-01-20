package com.pdm.sms.utils.timetable;

import com.pdm.sms.dao.Course.CourseInfoMapper;
import com.pdm.sms.domain.CourseInfo;
import com.pdm.sms.dto.WeekCourse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 14:23
 * @description 处理timetable中的week写入方式的工具类
 **/
@Component
public class WeekUtil {
    /*
1. `dealWeek(WeekCourse weekCourse)`：这个方法用于处理 `weekCourse` 对象中没有课程的时间段，将其设置为 "一"。
如果 `weekCourse` 对象的某一天（从星期一到星期日）为空或者是空字符串，那么这个方法就会把那一天设置为 "一"。

2. `dealMethod(List<WeekCourse> list, Map<String, Object> condition)`：


     */

    @Resource
    private CourseInfoMapper courseInfoMapper;


    //这个方法是处理没有课的时间段 把其空置为"一"
    public  void dealWeek(WeekCourse weekCourse) {
        if (weekCourse.getMonday() == null || weekCourse.getMonday().equals("")) {
            weekCourse.setMonday("一");
        }
        if (weekCourse.getTuesday() == null || weekCourse.getTuesday().equals("")) {
            weekCourse.setTuesday("一");
        }
        if (weekCourse.getWednesday() == null || weekCourse.getWednesday().equals("")) {
            weekCourse.setWednesday("一");
        }
        if (weekCourse.getThursday() == null || weekCourse.getThursday().equals("")) {
            weekCourse.setThursday("一");
        }
        if (weekCourse.getFriday() == null || weekCourse.getFriday().equals("")) {
            weekCourse.setFriday("一");
        }
        if (weekCourse.getSaturday() == null || weekCourse.getSaturday().equals("")) {
            weekCourse.setSaturday("一");
        }
        if (weekCourse.getSunday() == null || weekCourse.getSunday().equals("")) {
            weekCourse.setSunday("一");
        }
    }

    /**
     * 这个方法根据当前周过滤课程表中不属于当前周的课程。
     * 它遍历 `list` 中的每一个 `weekCourse` 对象，然后对每一天调用 `dealCourseInfo(map)` 方法，将返回的结果设置为那一天的值。
     * @param list
     * @param condition
     */
    public void dealMethod(List<WeekCourse> list, Map<String, Object> condition) {
        for (WeekCourse weekCourse : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("profession", condition.get("profession").toString());
            map.put("week", condition.get("week").toString());
            map.put("name", weekCourse.getMonday());
            weekCourse.setMonday(dealCourseInfo(map));
            map.put("name", weekCourse.getTuesday());
            weekCourse.setTuesday(dealCourseInfo(map));
            map.put("name", weekCourse.getWednesday());
            weekCourse.setWednesday(dealCourseInfo(map));
            map.put("name", weekCourse.getThursday());
            weekCourse.setThursday(dealCourseInfo(map));
            map.put("name", weekCourse.getFriday());
            weekCourse.setFriday(dealCourseInfo(map));
            map.put("name", weekCourse.getSaturday());
            weekCourse.setSaturday(dealCourseInfo(map));
            map.put("name", weekCourse.getSunday());
            weekCourse.setSunday(dealCourseInfo(map));
        }
    }

    /**
     *  这个方法首先从 `courseInfoMapper` 中获取 `CourseInfo` 对象。
     * 如果这个对象不为空，那么它会检查当前周（从 `map` 中获取）是否在课程的开始和结束周之间。
     * 如果不在这个范围内，那么返回 "一"；否则，返回 `map` 中的 "name" 值。
     * @param map
     * @return
     */
    public String dealCourseInfo(Map<String, Object> map) {
        CourseInfo courseInfo = courseInfoMapper.getInfo(map);
        if (courseInfo != null) {
            int start = courseInfo.getStart();
            int end = courseInfo.getEnd();
            int content = Integer.parseInt(map.get("week").toString());
            if (content < start || content > end) {
                return "一";
            }
        }
        return map.get("name").toString();
    }

    /**
     * 比较 oldWeek 对象的每一天（从星期一到星期日）是否等于 name。如果等于 name，
     * 那么就把 newWeek 对象的对应的那一天设置为 oldWeek 对象的那一天。
     * 所以，这个方法的主要作用是根据 oldWeek 对象的每一天的值来更新 newWeek 对象的每一天的值。
     * 是为了在处理课程信息时，根据某个条件（例如课程名）来更新新的课程表。
     * @param newWeek
     * @param oldWeek
     * @param name
     * @return
     */
    public WeekCourse dealWeekCourse(WeekCourse newWeek, WeekCourse oldWeek, String name) {

        if (oldWeek.getMonday().equals(name)) {
            newWeek.setMonday(oldWeek.getMonday());
        }
        if (oldWeek.getTuesday().equals(name)) {
            newWeek.setTuesday(oldWeek.getTuesday());
        }
        if (oldWeek.getWednesday().equals(name)) {
            newWeek.setWednesday(oldWeek.getWednesday());
        }
        if (oldWeek.getThursday().equals(name)) {
            newWeek.setThursday(oldWeek.getThursday());
        }
        if (oldWeek.getFriday().equals(name)) {
            newWeek.setFriday(oldWeek.getFriday());
        }
        if (oldWeek.getSaturday().equals(name)) {
            newWeek.setSaturday(oldWeek.getSaturday());
        }
        if (oldWeek.getSunday().equals(name)) {
            newWeek.setSunday(oldWeek.getSunday());
        }
        return newWeek;
    }

}
