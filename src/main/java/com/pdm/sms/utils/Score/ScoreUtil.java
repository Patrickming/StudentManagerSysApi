package com.pdm.sms.utils.Score;

import com.pdm.sms.dao.Score.ScoreMapper;
import com.pdm.sms.dto.Course;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

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

    public Map<String, Object> getLimit(List<Course> courseList) {
        List<Double> list = new ArrayList<>();
        double max = 0.00;
        double min = 0.00;
        double average = 0.00;
        double count = 0.00;
        for (Course course : courseList) {
            if (course.getScoreByUser() != null) {
                list.add(Double.parseDouble(course.getScoreByUser()));
            }
        }
        if (list.size() > 0) {
            Collections.sort(list);
            min = list.get(0);
            max = list.get(list.size() - 1);
        }

        for (Double score : list) {
            count += score;
        }
        average = list.size() == 0 ? 0.00 : count / list.size();
        Map<String, Object> map = new HashMap<>();
        map.put("max", max);
        map.put("min", min);
        map.put("average", average);
        return map;
    }

    public List<Map<String, Object>> dealScore(List<Course> courseList) {
        if (courseList.size() > 0) {
            Map<String, Object> unknownCondition = new HashMap<>();
            Map<String, Object> failCondition = new HashMap<>();
            Map<String, Object> passCondition = new HashMap<>();
            Map<String, Object> goodCondition = new HashMap<>();
            int fail = 0;
            int pass = 0;
            int good = 0;
            int unknown = 0;
            for (Course course : courseList) {
                if (course == null || course.getId() == null) {
                    continue;
                }
                double scoreFull = course.getScore() / 100;
                if (course.getScoreByUser() == null) {
                    unknown++;
                } else {
                    double score = Double.parseDouble(course.getScoreByUser()) * scoreFull;
                    if (score < 60) {
                        fail++;
                    } else if (score < 85) {
                        pass++;
                    } else {
                        good++;
                    }
                }
            }
            int passLine = (int) (courseList.get(0).getScore() * 0.6);
            int goodLine = (int) (courseList.get(0).getScore() * 0.85);
            int fullLine = courseList.get(0).getScore();
            String unknownLabel = "未录入";
            String failLabel = "不及格(" + "0-" + (passLine - 1) + ")";
            String passLabel = "及格(" + passLine + "-" + (goodLine - 1) + ")";
            String goodLabel = "优秀(" + goodLine + "-" + fullLine + ")";
            if (unknown != 0) {
                unknownCondition.put("label", unknownLabel);
                unknownCondition.put("value", unknown);
            }
            failCondition.put("label", failLabel);
            failCondition.put("value", fail);
            passCondition.put("label", passLabel);
            passCondition.put("value", pass);
            goodCondition.put("label", goodLabel);
            goodCondition.put("value", good);
            List<Map<String, Object>> list = new ArrayList<>();
            list.add(goodCondition);
            list.add(passCondition);
            list.add(failCondition);
            list.add(unknownCondition);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}
