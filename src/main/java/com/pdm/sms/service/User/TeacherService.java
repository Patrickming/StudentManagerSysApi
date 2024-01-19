package com.pdm.sms.service.User;

import com.pdm.sms.dto.User;

import java.util.List;

/**
 * @author xrm
 * @date 2024/1/17 16:27
 * @description 教师用户Service层接口
 **/
public interface TeacherService {
    /**
     * description: 新增教师账号
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
    void delete(List<Integer> ids);

    /**
     * description: 修改教师账号
     *
     * @param user
     * @return void
     */
    void update(User user);
}
