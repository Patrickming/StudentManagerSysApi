package com.pdm.sms.service.User.impl;

import com.pdm.sms.dao.User.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xrm
 * @date 2024/1/17 16:34
 * @description
 **/
@Service
public class AdminServiceImpl {
    @Resource
    private AdminMapper adminMapper;

}
