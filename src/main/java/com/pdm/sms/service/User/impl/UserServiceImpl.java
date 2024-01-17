package com.pdm.sms.service.User.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.pdm.sms.dao.User.AdminMapper;
import com.pdm.sms.dao.User.StudentMapper;
import com.pdm.sms.dao.User.TeacherMapper;
import com.pdm.sms.dao.User.UserMapper;
import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:29
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;


    @Override
    public User getStudentInfo(Map<String, Object> condition) {
        String keyValue = condition.get("level").toString();
        if (keyValue.equals("0")) {
            return userMapper.getAdminInfo(condition);
        } else if (keyValue.equals("1")) {
            return userMapper.getTeacherInfo(condition);
        } else {
            return userMapper.getStudentInfo(condition);
        }
    }

    @Override
    public String getToken(User user, long time) {
        if (user == null) {
            return "";
        }
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + time;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        JWTCreator.Builder builder = JWT.create().withAudience(user.getLevel().toString() + user.getId());
        token = builder.withIssuedAt(start).withExpiresAt(end)
                // 储存id和level
                .sign(Algorithm.HMAC256(user.getPassword())); // 储存password，用于解密
        return token;
    }

    @Override
    public User findUser(Map<String, Object> condition) {
        String id = condition.get("id").toString();
        String level = condition.get("level").toString();
        User user = new User();
        switch (level) {
            case "0":
                user = adminMapper.getUserById(id);
                break;
            case "1":
                user = teacherMapper.getUserById(id);
                break;
            case "2":
                user = studentMapper.getUserById(id);
                break;
        }
        return user;
    }
}
