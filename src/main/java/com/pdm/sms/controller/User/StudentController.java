package com.pdm.sms.controller.User;

import com.pdm.sms.dto.User;

import com.pdm.sms.service.User.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author xrm
 * @date 2024/1/18 14:54
 * @description 学生账号控制层
 **/
@RestController
@RequestMapping("/api/sms/user/student")
public class StudentController {
    @Resource
    private StudentService studentService;


    @PutMapping
    public void update(@RequestBody User user) {
        studentService.update(user);
    }

    @PostMapping
    public void addStudent(@RequestBody User user) {
        studentService.addStudent(user);
    }
    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") String[] ids) {
        List<String> idsList = Arrays.asList(ids);
        studentService.delete(idsList);
    }





}
