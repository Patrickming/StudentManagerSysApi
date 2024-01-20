package com.pdm.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/20 12:01
 * @description 课程具体排布实体表
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeekCourse {
    /**
     * id
     */
    private Integer id;
    /**
     * 周一
     */
    private String monday;
    /**
     * 周二
     */
    private String tuesday;
    /**
     * 周三
     */
    private String wednesday;
    /**
     * 周四
     */
    private String thursday;
    /**
     * 周五
     */
    private String friday;
    /**
     * 周六
     */
    private String saturday;
    /**
     * 周日
     */
    private String sunday;
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
    private Integer week;
}
