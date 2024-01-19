package com.pdm.sms.service.Course.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.Course.CourseMapper;
import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Course.CourseService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/19 23:01
 * @description 课程信息业务层
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition) {
        PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
        List<Course> courseList = courseMapper.getCourseList(pageRowBounds, condition);
        return new PagingResult<>(courseList, pageRowBounds.getTotal());
    }


}
