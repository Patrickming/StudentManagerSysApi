package com.pdm.sms.controller.Course;

import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Course.CourseService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/19 22:58
 * @description 课程控制层
 **/
@RestController
@RequestMapping("/api/sms/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/getCourseList")//前端分页请求参数
    private PagingResult<Course> getCourseList(@RequestParam Map<String, Object> condition,
                                               @RequestParam(required = false, name = "$limit", defaultValue = "10") Integer limit,
                                               @RequestParam(required = false, name = "$offset", defaultValue = "0") Integer offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return courseService.getCourseList(rowBounds, condition);
    }

}

