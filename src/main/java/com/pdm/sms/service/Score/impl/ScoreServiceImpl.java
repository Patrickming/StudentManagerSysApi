package com.pdm.sms.service.Score.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.Score.ScoreMapper;
import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Score.ScoreService;
import com.pdm.sms.utils.Score.ScoreUtil;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 22:53
 * @description
 **/
@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private ScoreUtil scoreUtil;

    @Override
    public PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition) {
        PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
        List<Course> courseList = new ArrayList<>();
        switch (condition.get("level").toString()) {
            case "0":
                courseList = scoreMapper.getCourseByAdmin(pageRowBounds, condition);
                for (Course course : courseList) {
                    scoreUtil.adminCourseMethod(course);
                }
                break;
            case "1":
                courseList = scoreMapper.getCourseByMap(pageRowBounds, condition);
                break;
            case "2":
                courseList = scoreMapper.getCourseByStudent(pageRowBounds, condition);
                break;
            default:
        }
        return new PagingResult<>(courseList, pageRowBounds.getTotal());
    }


}
