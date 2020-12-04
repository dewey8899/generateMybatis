package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbUserRole;
import com.company.project.service.TbUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by dewey on 2020/04/01
*/

@RequestMapping("/tb/user/role")
@RestController
public class TbUserRoleController {
    @Autowired
    private TbUserRoleService tbUserRoleService;

    /**
     *添加用户
     * @param tbUserRole
     * @return
     */
    @PostMapping(path = "/add")
    public Result add(@RequestBody TbUserRole tbUserRole) {
        tbUserRoleService.save(tbUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbUserRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbUserRole tbUserRole) {
        tbUserRoleService.update(tbUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbUserRole tbUserRole = tbUserRoleService.findById(id);
        return ResultGenerator.genSuccessResult(tbUserRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbUserRole> list = tbUserRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
