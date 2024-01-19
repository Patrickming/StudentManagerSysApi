package com.pdm.sms.service.User.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.User.AdminMapper;
import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.AdminService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/17 16:34
 * @description
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public void add(User user) {
        int num = adminMapper.checkCodeCount();
        String username = "980502" + num;
        user.setUsername(username);
        user.setPassword("123456");
        adminMapper.add(user);
    }

    @Override
    public void delete(List<Integer> ids) {
        adminMapper.delete(ids);
    }

    @Override
    public void update(User user) {
        adminMapper.update(user);
    }

    @Override
    public PagingResult<User> getAdminList(RowBounds rowBounds, Map<String, Object> condition) {
        PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
        List<User> studentInfoList = adminMapper.getAdminList(pageRowBounds, condition);
        return new PagingResult<>(studentInfoList, pageRowBounds.getTotal());
    }
}
