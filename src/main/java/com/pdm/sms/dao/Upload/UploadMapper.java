package com.pdm.sms.dao.Upload;

import com.pdm.sms.domain.Upload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 17:27
 * @description 上传
 **/
@Mapper
public interface UploadMapper {
    /**
     * description: 上传头像
     * @param: condition
     * return: void
     */
    void add(Upload upload);
    /**
     * description: 获取头像
     * @param: condition
     * return: String
     */
    String getHead(@Param("condition") Map<String, Object> condition);
    /**
     * description: 根据用户id和等级查询用户是否存在
     * @param: condition
     * return: Integer
     */
    Integer checkCount(@Param("condition") Map<String, Object> condition);
    /**
     * description: 修改用户头像
     * @param: upload
     * return: void
     */
    void update(Upload upload);
}

