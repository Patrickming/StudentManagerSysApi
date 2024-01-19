package com.pdm.sms.service.User;


import com.pdm.sms.dto.User;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:25
 * @description 管理员用户Service层接口
 **/
public interface AdminService {
    /**
     * description: 新增学生账号
     *
     * @param user
     * @return void
     */
    void add(User user);

    /**
     * description: 删除学生账号
     *
     * @param ids
     * @return void
     */
    void delete(List<Integer> ids);

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
    PagingResult<User> getAdminList(RowBounds rowBounds, Map<String, Object> condition);
}
