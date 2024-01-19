package com.pdm.sms.service.User;

import com.pdm.sms.dto.User;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

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

    /**
     * description: 获取学生账号信息列表
     * @param rowBounds
     * @param condition
     * @return com.pdm.sms.utils.PagingResult
     */
    PagingResult<User> getStudentList(RowBounds rowBounds, Map<String, Object> condition);
}

