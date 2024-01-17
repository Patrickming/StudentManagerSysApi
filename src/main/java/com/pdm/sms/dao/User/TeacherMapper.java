package com.pdm.sms.dao.User;

import com.pdm.sms.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xrm
 * @date 2024/1/17 16:21
 * @description 教师用户mapper层
 **/
@Mapper
public interface TeacherMapper {
    /**
     * description: 根据管理员id获取管理员信息
     * @param: String
     * return: User
     */
    User getUserById(@Param("id") String id);
}
