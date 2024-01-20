package com.pdm.sms.controller.Course;

import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Course.CourseService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") String[] ids) {
        List<Long> idsList = Stream.of(ids).map(Long::parseLong).collect(Collectors.toList());
        courseService.delete(idsList);
    }

    @PutMapping
    public void update(@RequestBody Course course) {
        courseService.update(course);
    }

    @GetMapping("/getCourseByMap")
    private List<Course> getCourseByMap(@RequestParam Map<String, Object> condition) {
        return courseService.getCourseByMap(condition);
    }
}

