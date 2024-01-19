package com.pdm.sms.service.User.impl;

import com.github.pagehelper.PageRowBounds;
import com.pdm.sms.dao.Profession.ProfessionMapper;
import com.pdm.sms.dao.User.StudentMapper;
import com.pdm.sms.domain.Profession;
import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.StudentService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xrm
 * @date 2024/1/18 15:16
 * @description 学生用户业务层
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ProfessionMapper professionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) //出现任意异常都回滚
    public void addStudent(User user) {
        int professionId = 0;
        //判断用户输入的专业是否存在，若存在则获取专业id，若不存在则创建专业
        int count = professionMapper.checkProfessionCount(user.getProfession());
        Profession profession;
        if (count > 0) {
            profession = professionMapper.getProfessionIdByName(user.getProfession());
        } else {
            profession = new Profession();
            profession.setName(user.getProfession());
            professionMapper.addProfession(profession);
        }
        professionId = profession.getId();
        String professionStr = "";
        //添加前缀
        if (professionId < 10) {
            professionStr = "0" + professionId;
        } else {
            professionStr = Integer.toString(professionId);
        }
        Map<String, Object> condition = new HashMap<>();
        condition.put("profession", user.getProfession());
        condition.put("grade", user.getGrade());
        int num = studentMapper.checkCodeCount(condition) + 1;
        String str = "";
        if (num < 10) {
            str = "0" + num;
        } else if (num < 100) {
            str = Integer.toString(num);
        }
        String no = "3" + user.getAdmissionTime().substring(user.getAdmissionTime().length() - 2)
                + "89" + professionStr + user.getGrade().substring(user.getGrade().length() - 1) + str;
        user.setId(no);
        user.setUsername(no);
        user.setPassword("123456");
        studentMapper.addStudent(user);
    }

    @Override
    public void delete(List<String> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void update(User user) {
        studentMapper.update(user);
    }

    @Override
    public PagingResult<User> getStudentList(RowBounds rowBounds, Map<String, Object> condition) {
        PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
        List<User> StudentInfoList = studentMapper.getStudentList(pageRowBounds, condition);
        return new PagingResult<>(StudentInfoList, pageRowBounds.getTotal());
    }
}
