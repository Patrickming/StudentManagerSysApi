package com.pdm.sms.service.User;

import com.pdm.sms.dto.User;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:24
 * @description 登陆用户service层接口
 **/
public interface UserService {
    /**
     * description: 根据用户、密码登陆
     *
     * @params: condition
     * @return: com.pdm.sms.dto.User
     */
    User getStudentInfo(Map<String, Object> condition);

    /**
     * description: 获取token
     * @param: User
     * return: String
     */
    String getToken(User user, long time);
    /**
     * description: 根据等级和id获取用户信息
     * @param: condition
     * return: User
     */
    User findUser(Map<String, Object> condition);

    /**
     * description: 修改密码
     * @param condition
     * @return void
     */
    boolean update(Map<String, Object> condition);
}
