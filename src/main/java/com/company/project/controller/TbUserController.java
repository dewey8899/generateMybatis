package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbUser;
import com.company.project.service.TbUserService;
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
@RequestMapping("/tb/user")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    @PostMapping("/add")
    public Result add(TbUser tbUser) {
        tbUserService.save(tbUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbUser tbUser) {
        tbUserService.update(tbUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbUser tbUser = tbUserService.findById(id);
        return ResultGenerator.genSuccessResult(tbUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbUser> list = tbUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
