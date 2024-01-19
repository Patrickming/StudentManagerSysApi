package com.pdm.sms.service.User.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.pdm.sms.dao.User.AdminMapper;
import com.pdm.sms.dao.User.StudentMapper;
import com.pdm.sms.dao.User.TeacherMapper;
import com.pdm.sms.dao.User.UserMapper;
import com.pdm.sms.dto.User;
import com.pdm.sms.service.User.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xrm
 * @date 2024/1/17 16:29
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;


    @Override
    public User getStudentInfo(Map<String, Object> condition) {
        String keyValue = condition.get("level").toString();
        if (keyValue.equals("0")) {
            return userMapper.getAdminInfo(condition);
        } else if (keyValue.equals("1")) {
            return userMapper.getTeacherInfo(condition);
        } else {
            return userMapper.getStudentInfo(condition);
        }
    }

    @Override
    public String getToken(User user, long time) {
        if (user == null) {
            return "";
        }
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + time;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        JWTCreator.Builder builder = JWT.create().withAudience(user.getLevel().toString() + user.getId());
        token = builder.withIssuedAt(start).withExpiresAt(end)
                // 储存id和level
                .sign(Algorithm.HMAC256(user.getPassword())); // 储存password，用于解密
        return token;
    }

    @Override
    public User findUser(Map<String, Object> condition) {
        String id = condition.get("id").toString();
        String level = condition.get("level").toString();
        User user = new User();
        switch (level) {
            case "0":
                user = adminMapper.getUserById(id);
                break;
            case "1":
                user = teacherMapper.getUserById(id);
                break;
            case "2":
                user = studentMapper.getUserById(id);
                break;
        }
        return user;
    }

    @Override
    public boolean update(Map<String, Object> condition) {
        //因为是修改不同的表 所以要判断 交给数据库去进行条件判断
        switch (condition.get("level").toString()) {
            case "0":
                condition.put("table", "admin");
                break;
            case "1":
                condition.put("table", "teacher");
                break;
            case "2":
                condition.put("table", "student");
                break;
        }
        Integer num = userMapper.checkPasswordCount(condition);
        if (num != 0) {
            userMapper.update(condition);
        }
        return num != 0;
    }


    /**
     * 这段代码是在构建一个树形结构的数据，主要用于前端展示：
     * 前端使用返回的`treeList`来构建一个树形结构，展示学生、教师和管理员的信息，其中学生信息按照专业和年级进行了分类。这是一种常见的前端数据处理方式，用于展示复杂的层级关系。
     * @return treeList
     */
    @Override
    public List<Object> getTree() {
        // 获取专业
        List<User> studentList = studentMapper.getStudentTree();
        List<Map<String, Object>> professionList = new ArrayList<>();
        //  转化为前端树形结构所需的数据格式

        //从学生列表中提取专业信息，并去重。
        Set<String> proSet = studentList.stream().map(User::getProfession).collect(Collectors.toSet());
        /*
        3. 对每一个专业，执行以下操作：
     *    - 创建一个新的Map对象`stuProfessionTreeObj`，并将专业名称作为"label"键的值。
     *    - 根据专业名称，从数据库中获取对应的年级列表。
     *    - 将年级列表转换为Set以去重，然后排序。
     *    - 对每一个年级，创建一个新的Map对象，将年级作为"label"键的值，然后添加到年级列表`gradeTreeList`中。
     *    - 将年级列表作为"children"键的值，添加到`stuProfessionTreeObj`中。
     *    - 将`stuProfessionTreeObj`添加到专业列表`professionList`中。
         */
        for (String profession : proSet) {
            Map<String, Object> stuProfessionTreeObj = new HashMap<>();

            stuProfessionTreeObj.put("label", profession);
            //得到年级列表
            List<String> gradeList = studentMapper.getGradeByProfession(profession);

            gradeList = new HashSet<>(gradeList).stream().sorted(Comparator.comparing(Integer::new)).collect(Collectors.toList());

            // 转成int，然后再排序
            List<Map<String, Object>> gradeTreeList = new ArrayList<>();
            for (String grade : gradeList) {
                Map<String, Object> gradeTreeMap = new HashMap<>();
                gradeTreeMap.put("label", grade);
                gradeTreeList.add(gradeTreeMap);
            }

            stuProfessionTreeObj.put("children", gradeTreeList);
            professionList.add(stuProfessionTreeObj);
        }

       // 创建一个新的列表`treeList`，并添加三个Map对象，分别代表学生、教师和管理员。其中，学生对象的"children"键的值为`professionList`
        ArrayList<Object> treeList = new ArrayList<>();
        Map<String, Object> studentObj = new HashMap<>();
        Map<String, Object> teacherObj = new HashMap<>();
        Map<String, Object> adminObj = new HashMap<>();
        studentObj.put("label", "学生");
        studentObj.put("children", professionList);
        teacherObj.put("label", "教师");
        adminObj.put("label", "管理员");
        treeList.add(studentObj);
        treeList.add(teacherObj);
        treeList.add(adminObj);
        return treeList;
    }
}


