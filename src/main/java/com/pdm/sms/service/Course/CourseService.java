package com.pdm.sms.service.Course;

import com.pdm.sms.dto.Course;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/19 23:00
 * @description 课程信息service层
 **/
public interface CourseService {

    /**
     * description: 查询课程信息
     *
     * @param: rowBounds
     * @param: condition
     * return: com.pdm.sms.utils.PagingResult
     */
    PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition);

    /**
     * description: 新增课程信息
     *
     * @param course return void
     */
    void addCourse(Course course);

    /**
     * description: 删除课程信息
     *
     * @param ids
     * @return void
     */
    void delete(List<Long> ids);

    /**
     * description: 修改课程信息
     *
     * @param course
     * @return void
     */
    void update(Course course);
}
