package com.pdm.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/20 11:52
 * @description 课程表实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timetable {
    /**
     * id
     */
    private Integer id;
    /**
     * week id
     */
    private Integer weekId;
    /**
     * 专业
     */
    private String profession;
    /**
     * 班级
     */
    private String grade;
    /**
     * 学年
     */
    private Integer year;
    /**
     * 学期
     */
    private Integer term;

    /**
     * 周数
     */
    private Integer weekNum;

}
