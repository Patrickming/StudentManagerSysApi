package com.pdm.sms.dao.Profession;

import com.pdm.sms.domain.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xrm
 * @date 2024/1/19 9:32
 * @description 专业表持久层
 **/

@Mapper
public interface ProfessionMapper {
    /**
     * description: 根据专业名查询专业数量（判断是否存在该专业）
     *
     * @param: String
     * return: Integer
     */
    Integer checkProfessionCount(@Param("name") String name);

    /**
     * description: 根据专业名查询专业信息
     *
     * @param: String
     * return: Profession
     */
    Profession getProfessionIdByName(@Param("name") String name);

    /**
     * description: 新增专业
     *
     * @param: Profession
     * return: void
     */
    void addProfession(Profession profession);

    /**
     * description: 获取所有专业
     * return: List<Profession>
     */
    List<Profession> getProfessionList();
}

