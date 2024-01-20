package com.pdm.sms.service.Score;

import com.pdm.sms.dto.Course;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 16:49
 * @description 成绩查询
 **/
public interface ScoreService {
    /**
     * description: 根据班级等信息获取课程信息
     *
     * @param rowBounds
     * @param condition
     * @return com.pdm.sms.utils.PagingResult
     */
    PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition);

    /**
     * description: 导出成绩到Excel
     *
     * @param: Map<String, Object> condition
     * return: List<Course>
     */
    List<Course> getExportList(Map<String, Object> condition);

}
