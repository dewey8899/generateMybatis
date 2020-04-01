package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbPermissionRole;
import com.company.project.service.TbPermissionRoleService;
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
@RequestMapping("/tb/permission/role")
public class TbPermissionRoleController {
    @Autowired
    private TbPermissionRoleService tbPermissionRoleService;

    @PostMapping("/add")
    public Result add(TbPermissionRole tbPermissionRole) {
        tbPermissionRoleService.save(tbPermissionRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbPermissionRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbPermissionRole tbPermissionRole) {
        tbPermissionRoleService.update(tbPermissionRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbPermissionRole tbPermissionRole = tbPermissionRoleService.findById(id);
        return ResultGenerator.genSuccessResult(tbPermissionRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbPermissionRole> list = tbPermissionRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
