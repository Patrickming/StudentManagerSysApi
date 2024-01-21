package com.pdm.sms.controller.Score;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pdm.sms.dto.Course;
import com.pdm.sms.dto.Score;
import com.pdm.sms.service.Score.ScoreService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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

    @GetMapping("/export")
    public List<Course> getExportList(@RequestParam Map<String, Object> condition) {
        return scoreService.getExportList(condition);
    }

    @PostMapping
    private void addEntry(@RequestBody JSONArray userScore) {
        List<Score> list = JSONObject.parseArray(userScore.toJSONString(), Score.class);
        scoreService.addEntry(list);
    }

    @GetMapping("/getUserNum")
    public List<Map<String, Object>> getUserNum(@RequestParam Map<String, Object> condition) {
        return scoreService.getUserNum(condition);
    }

    @GetMapping("/getUserTotal")
    public Map<String, Object> getUserTotal(@RequestParam Map<String, Object> condition) {
        return scoreService.getUserTotal(condition);
    }



}
