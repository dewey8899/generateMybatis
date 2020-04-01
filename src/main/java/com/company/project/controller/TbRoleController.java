package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbRole;
import com.company.project.service.TbRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by dewey on 2020/04/01
*/
@RestController
@RequestMapping("/tb/role")
public class TbRoleController {
    @Autowired
    private TbRoleService tbRoleService;

    @PostMapping("/add")
    public Result add(TbRole tbRole) {
        tbRoleService.save(tbRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbRole tbRole) {
        tbRoleService.update(tbRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbRole tbRole = tbRoleService.findById(id);
        return ResultGenerator.genSuccessResult(tbRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbRole> list = tbRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
