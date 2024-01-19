package com.pdm.sms.dao.User;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author xrm
 * @date 2024/1/17 16:19
 * @description 管理员用户mapper层
 **/
@Mapper
public interface AdminMapper {
    /**
     * description: 新增管理员账号信息
     * @param user
     * @return void
     */
    void add(User user);

    /**
     * description: 删除管理员账号
     *
     * @param ids
     * @return void
     */
    void delete(@Param("ids") List<Integer> ids);

    /**
     * description: 修改管理员账号
     *
     * @param user
     * @return void
     */
    void update(User user);
    /**
     * description: 根据管理员id获取管理员信息
     * @param: String
     * return: User
     */
    User getUserById(@Param("id") String id);
    /**
     * description: 查看管理员人数
     * return: Integer
     */
    Integer checkCodeCount();

    /**
     * description: 获取管理员账号信息列表
     * @param rowBounds
     * @return java.util.List<com.zjh.sms.dto.User>
     */
    List<User> getAdminList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

}
