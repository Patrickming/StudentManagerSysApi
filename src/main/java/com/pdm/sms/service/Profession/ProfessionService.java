package com.pdm.sms.service.Profession;

import com.pdm.sms.domain.Profession;

import java.util.List;

/**
 * @author xrm
 * @date 2024/1/19 17:00
 * @description 专业信息Service层
 **/
public interface ProfessionService {
    /**
     * description: 获取专业
     * return:List<Profession>
     */
    List<Profession> getProfessionList();
}
