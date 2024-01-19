package com.pdm.sms.dao.User;

import com.pdm.sms.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xrm
 * @date 2024/1/17 16:21
 * @description 教师用户mapper层
 **/
@Mapper
public interface TeacherMapper {
    /**
     * description: 根据管理员id获取管理员信息
     *
     * @param: String
     * return: User
     */
    User getUserById(@Param("id") String id);


    /**
     * description: 新增教师账号信息
     *
     * @param user
     * @return void
     */
    void addTeacher(User user);

    /**
     * description: 删除教师账号
     *
     * @param ids
     * @return void
     */
    void delete(@Param("ids") List<Integer> ids);

    /**
     * description: 修改教师账号
     *
     * @param user
     * @return void
     */
    void update(User user);


    /**
     * description: 查看人数
     * return: Integer
     */
    Integer checkCodeCount();
}
