package com.pdm.sms.controller.User;

import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.TeacherService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/18 14:55
 * @description 教师账号控制层
 **/
@RestController
@RequestMapping("/api/sms/user/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @PutMapping
    public void update(@RequestBody User user) {
        teacherService.update(user);
    }

    @PostMapping
    public void addTeacher(@RequestBody User user) {
        teacherService.addTeacher(user);
    }

    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        teacherService.delete(idsList);
    }


}

