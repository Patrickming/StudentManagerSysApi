package com.pdm.sms.service.User;

import com.pdm.sms.dto.User;

import java.util.List;

/**
 * @author xrm
 * @date 2024/1/17 16:27
 * @description 学生用户Service层接口
 **/
public interface StudentService {
    /**
     * description: 新增学生账号
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
    void delete(List<String> ids);

    /**
     * description: 修改学生账号
     *
     * @param user
     * @return void
     */
    void update(User user);

}
