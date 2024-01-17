package com.pdm.sms.dao.User;

import com.pdm.sms.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:16
 * @description 登陆用户mapper层
 **/
@Mapper
public interface UserMapper {
    /**
     * description: 根据学生用户、密码登陆
     * @params: condition
     * @return: com.pdm.sms.dto.User
     */
    User getStudentInfo(@Param("condition") Map<String, Object> condition);//如果在xml文件里使用了动态sql里使用了condition 就得用@Param("condition")指定
    /**
     * description: 根据老师用户、密码登陆
     * @params: condition
     * @return: com.pdm.sms.dto.User
     */
    User getTeacherInfo(@Param("condition") Map<String, Object> condition);
    /**
     * description: 根据管理员用户、密码登陆
     * @params: condition
     * @return: com.pdm.sms.dto.User
     */
    User getAdminInfo(@Param("condition") Map<String, Object> condition);

}
