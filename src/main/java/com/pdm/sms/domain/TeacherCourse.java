package com.pdm.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/19 16:49
 * @description 教师课程任命表实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCourse {
    /**
     * id
     */
    private Integer id;
    /**
     * 教师id
     */
    private String teacherId;
    /**
     * 教师用户名
     */
    private String username;
    /**
     * 课程名
     */
    private String name;
    /**
     * 专业
     */
    private String profession;
    /**
     * 班级
     */
    private String grade;
    /**
     * 学期
     */
    private Integer term;

    /**
     * 课程id
     */
    private String courseId;

    private CourseInfo courseInfo;

}
