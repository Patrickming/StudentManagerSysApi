package com.pdm.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrm
 * @date 2024/1/19 9:21
 * @description 专业表实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profession {
    /**
     * 专业id
     */
    private Integer id;
    /**
     * 专业名
     */
    private String name;
}
