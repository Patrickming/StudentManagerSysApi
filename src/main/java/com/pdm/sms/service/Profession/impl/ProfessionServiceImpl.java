package com.pdm.sms.service.Profession.impl;

import com.pdm.sms.dao.Profession.ProfessionMapper;
import com.pdm.sms.domain.Profession;
import com.pdm.sms.service.Profession.ProfessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xrm
 * @date 2024/1/19 22:43
 * @description 专业表service层
 **/
@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Resource
    private ProfessionMapper professionMapper;

    @Override
    public List<Profession> getProfessionList() {
        return professionMapper.getProfessionList();
    }
}
