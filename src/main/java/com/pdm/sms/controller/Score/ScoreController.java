package com.pdm.sms.controller.Score;

import com.pdm.sms.dto.Course;
import com.pdm.sms.service.Score.ScoreService;
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
 * @date 2024/1/20 16:48
 * @description 成绩查询控制器
 **/
@RestController
@RequestMapping("/api/sms/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @GetMapping("/getCourseList")
    public PagingResult<Course> getCourseList(@RequestParam Map<String, Object> condition,
                                              @RequestParam(required = false, name = "$limit", defaultValue = "10") Integer limit,
                                              @RequestParam(required = false, name = "$offset", defaultValue = "0") Integer offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return scoreService.getCourseList(rowBounds, condition);
    }

}
