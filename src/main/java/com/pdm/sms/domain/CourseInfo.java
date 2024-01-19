package com.pdm.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/19 16:49
 * @description 课程具体安排
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfo {
    /**
     * id
     */
    private Integer id;
    /**
     * 课程id
     */
    private String courseId;
    /**
     *周数 start
     */
    private Integer start;
    /**
     * 周数 end
     */
    private Integer end;
    /**
     * 教室
     */
    private String room;
    /**
     * 专业
     */
    private String profession;

}
