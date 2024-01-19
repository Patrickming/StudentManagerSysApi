package com.pdm.sms.controller.Profession;

import com.pdm.sms.domain.Profession;
import com.pdm.sms.service.Profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xrm
 * @date 2024/1/19 22:47
 * @description 所有专业控制层
 **/
@RestController
@RequestMapping("/api/sms/profession")
public class ProfessionController {

    @Resource
    private ProfessionService professionService;

    @GetMapping("/getProfessionList")
    private List<Profession> getProfessionList () {
        return professionService.getProfessionList();
    }
}
