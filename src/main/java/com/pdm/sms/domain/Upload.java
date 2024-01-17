package com.pdm.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/17 17:29
 * @description 上传
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Upload {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户 id
     */
    private String userId;
    /**
     * 用户等级
     */
    private Integer level;
    /**
     * 储存地址
     */
    private String url;
}
