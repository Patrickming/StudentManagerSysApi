package com.pdm.sms.utils.Score;

import com.pdm.sms.dao.Score.ScoreMapper;
import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Score.ScoreService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/20 22:57
 * @description
 **/
@Component
public class ScoreUtil {
    @Resource
    private ScoreMapper scoreMapper;

    public void adminCourseMethod(Course course) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", course.getNo());
        map.put("courseName", course.getName());
        Course courseById = scoreMapper.getScoreById(map);
        if (courseById != null) {
            course.setCreditsByUser(courseById.getCreditsByUser());
            course.setPointByUser(courseById.getPointByUser());
            course.setScoreByUser(courseById.getScoreByUser());
        }
    }
}
