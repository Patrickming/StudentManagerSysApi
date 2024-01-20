package com.pdm.sms.service.TeacherCourse.impl;

import com.pdm.sms.dao.Course.CourseMapper;
import com.pdm.sms.dao.Profession.ProfessionMapper;
import com.pdm.sms.dao.TeacherCourse.TeacherCourseMapper;
import com.pdm.sms.dao.User.StudentMapper;
import com.pdm.sms.domain.Profession;
import com.pdm.sms.dto.Course;
import com.pdm.sms.service.TeacherCourse.TeacherCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xrm
 * @date 2024/1/20 9:45
 * @description
 **/
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Resource
    private ProfessionMapper professionMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;


    @Override
    public List<Map<String, Object>> getProfessionInfoByAdmin() {
        List<Map<String, Object>> arr = new ArrayList<>();
        List<Profession> professionList = professionMapper.getProfessionList();
        for (Profession profession : professionList) {
            Map<String, Object> condition = new HashMap<>();
            condition.put("profession", profession.getName());
            // 获取该专业的年级
            List<String> gradeList = studentMapper.getGradeByProfession(profession.getName());
            //根据专业、学期获取课程列表
            List<Course> courseList = courseMapper.getCourseByMap(condition);
            List<String> courseNameList = new ArrayList<>();
            for (Course course : courseList) {
                courseNameList.add(course.getName());
            }

            // 转成int，然后再排序
            List<Integer> list = new ArrayList<>();
            for (String str : new HashSet<>(gradeList)) {
                int number = Integer.parseInt(str);
                list.add(number);
            }
            Collections.sort(list);

            condition.put("grade", list);
            condition.put("course", new HashSet<>(courseNameList));
            arr.add(condition);
        }
        return arr;
    }

//    @Override
//    public List<Map<String, Object>> getProfessionInfoByTeacher(String teacherId) {
//        List<TeacherCourse> list = teacherCourseMapper.getCourseListById(teacherId);
//        List<Map<String, Object>> arr = new ArrayList<>();
//        Set<String> professionSet = new HashSet<>();
//        for (TeacherCourse teacherCourse : list) {
//            professionSet.add(teacherCourse.getProfession());
//        }
//        for (String s : professionSet) {
//            Map<String, Object> condition = new HashMap<>();
//            condition.put("teacherId", teacherId);
//            condition.put("profession", s);
//            List<TeacherCourse> listObj = teacherCourseMapper.getGradeInfoByMap(condition);
//            Set<String> gradeSet = new HashSet<>();
//            Set<String> courseSet = new HashSet<>();
//            for (TeacherCourse teacherCourse : listObj) {
//                gradeSet.add(teacherCourse.getGrade());
//                courseSet.add(teacherCourse.getName());
//            }
//            condition.put("grade", gradeSet);
//            condition.put("course", courseSet);
//            arr.add(condition);
//        }
//        return arr;
//    }

}
