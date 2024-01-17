package com.pdm.sms.service.Upload;

import com.pdm.sms.domain.Upload;

import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 17:24
 * @description 上传文件service层
 **/

public interface UploadService {
    /**
     * description: 上传头像
     * @param: upload
     * return: void
     * Author: rabbiter
     * @Date: 2022/4/29 11:40
     */
    void upload(Upload upload);
    /**
     * description: 获取头像
     * @param: condition
     * return: String
     * Author: rabbiter
     * @Date: 2022/4/29 14:02
     */
    String getHeader(Map<String, Object> condition);
}

