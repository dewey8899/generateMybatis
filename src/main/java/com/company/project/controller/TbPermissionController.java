package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbPermission;
import com.company.project.service.TbPermissionService;
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
@RequestMapping("/tb/permission")
public class TbPermissionController {
    @Autowired
    private TbPermissionService tbPermissionService;

    @PostMapping("/add")
    public Result add(TbPermission tbPermission) {
        tbPermissionService.save(tbPermission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbPermissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbPermission tbPermission) {
        tbPermissionService.update(tbPermission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbPermission tbPermission = tbPermissionService.findById(id);
        return ResultGenerator.genSuccessResult(tbPermission);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbPermission> list = tbPermissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
