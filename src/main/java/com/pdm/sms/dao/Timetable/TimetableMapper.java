package com.pdm.sms.dao.Timetable;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 12:05
 * @description 课程表mapper类
 **/
@Mapper
public interface TimetableMapper {
    /**
     * description: 根据专业班级查看课程表数量（验证是否添加课程表）
     *
     * @param: Map<String, Object> condition
     * return: Integer
     */
    Integer checkCount(@Param("condition") Map<String, Object> condition);
}
