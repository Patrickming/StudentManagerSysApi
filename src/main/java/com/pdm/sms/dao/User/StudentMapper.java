package com.pdm.sms.dao.User;

import com.pdm.sms.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:20
 * @description 学生用户mapper层
 **/
@Mapper
public interface StudentMapper {
    /**
     * description: 根据管理员id获取管理员信息
     *
     * @param: String
     * return: User
     */
    User getUserById(@Param("id") String id);

    /**
     * description: 新增学生账号信息
     *
     * @param user
     * @return void
     */
    void addStudent(User user);

    /**
     * description: 删除学生账号
     *
     * @param ids
     * @return void
     */
    void delete(@Param("ids") List<String> ids);

    /**
     * description: 修改学生账号
     *
     * @param user
     * @return void
     */
    void update(User user);

    /**
     * description: 查看人数
     * return: Integer
     */
    Integer checkCodeCount(@Param("condition") Map<String, Object> condition);
}
