package com.pdm.sms.service.User.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.User.TeacherMapper;
import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.TeacherService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/18 15:18
 * @description 教师用户业务层
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public void addTeacher(User user) {
        int num = teacherMapper.checkCodeCount();
        String str = "";
        if (num < 10) {
            str = "00" + num;
        } else if (num < 100) {
            str = "0" + num;
        } else {
            str = Integer.toString(num);
        }
        String no = "389" + str + user.getSex().toString(); //默认前缀
        user.setUsername(no);
        user.setId(no);
        user.setPassword("123456"); //默认密码
        teacherMapper.addTeacher(user);
    }

    @Override
    public void delete(List<Integer> ids) {
        teacherMapper.delete(ids);
    }

    @Override
    public void update(User user) {
        teacherMapper.update(user);
    }

    @Override
    public PagingResult<User> getTeacherList(RowBounds rowBounds, Map<String, Object> condition) {
        PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
        List<User> TeacherInfoList = teacherMapper.getTeacherList(pageRowBounds, condition);
        return new PagingResult<>(TeacherInfoList, pageRowBounds.getTotal());
    }
}
