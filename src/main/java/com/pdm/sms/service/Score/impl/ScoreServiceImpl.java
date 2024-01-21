package com.pdm.sms.service.Score.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.Score.ScoreMapper;
import com.pdm.sms.dto.Course;
import com.pdm.sms.dto.Score;
import com.pdm.sms.service.Score.ScoreService;
import com.pdm.sms.utils.Score.ScoreUtil;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public List<Course> getExportList(Map<String, Object> condition) {
        List<Course> courseList = new ArrayList<>();
        switch (condition.get("level").toString()) {
            case "0":
                courseList = scoreMapper.getExportListByAdmin(condition);
                for (Course course : courseList) {
                    scoreUtil.adminCourseMethod(course);
                }
                break;
            case "1":
                courseList = scoreMapper.getExportList(condition);
                break;
            case "2":
                courseList = scoreMapper.getExportListByStudent(condition);
                break;
            default:
        }
        return courseList;
    }

    @Override
    public void addEntry(List<Score> list) {
        for (Score score : list) {
            // string转double
            double scoreByUser = Double.parseDouble(score.getScoreByUser());
            BigDecimal bg = new BigDecimal((scoreByUser/10-5));
            // 取两位有效数字
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            String point = scoreByUser > 59 ? String.valueOf(f1):"0";
            String credits = scoreByUser >= score.getScore()*0.6 ? score.getCredits() : "0.00";
            score.setPointByUser(point);
            score.setCreditsByUser(credits);
            score.setCourseId(Long.toString(score.getId()));
            Map<String, Object> condition = new HashMap<>();
            condition.put("StudentId", score.getNo());
            condition.put("CourseName", score.getName());
            Integer num = scoreMapper.checkCount(condition);
            if (num == 0) {
                scoreMapper.addEntry(score);
            } else {
                scoreMapper.updateEntry(score);
            }
        }
    }
}
