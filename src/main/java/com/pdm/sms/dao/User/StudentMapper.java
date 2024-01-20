package com.pdm.sms.dao.User;

import com.github.pagehelper.PageRowBounds;
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

    /**
     * description: 获取学生账号信息列表
     *
     * @param rowBounds
     * @return java.util.List<com.pdm.sms.dto.User>
     */
    List<User> getStudentList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    /**
     * description:得到账户列表
     */
    List<User> getStudentTree();

    /**
     * description: 根据专业获取该专业下的所有班级
     *
     * @param: String
     * return: List<String>
     */
    List<String> getGradeByProfession(@Param("code") String code);

    /**
     * description: 根据id获取学生信息
     */
    User getStudentById(@Param("id") String id);
}
