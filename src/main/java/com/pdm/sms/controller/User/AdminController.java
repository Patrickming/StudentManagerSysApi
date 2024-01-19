package com.pdm.sms.controller.User;

/**
 * @author xrm
 * @date 2024/1/18 14:52
 * @description 管理员账户控制层
 **/

import com.pdm.sms.dto.User;

import com.pdm.sms.service.User.AdminService;
import com.pdm.sms.utils.page.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sms/user/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PutMapping
    public void update(@RequestBody User user) {
        adminService.update(user);
    }

    @PostMapping
    public void addAdmin(@RequestBody User user) {
        adminService.add(user);
    }

    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        adminService.delete(idsList);
    }

    @GetMapping("/getAdminList")
    public PagingResult<User> getAdminList(@RequestParam Map<String, Object> condition,
                                           @RequestParam(required = false, name = "$limit", defaultValue = "10") Integer limit,
                                           @RequestParam(required = false, name = "$offset", defaultValue = "0") Integer offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return adminService.getAdminList(rowBounds, condition);
    }




}
