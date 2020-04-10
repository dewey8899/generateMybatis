package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbDeliveryBase;
import com.company.project.service.TbDeliveryBaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by dewey on 2020/04/09
*/
@RestController
@RequestMapping("/tb/delivery/base")
public class TbDeliveryBaseController {
    @Autowired
    private TbDeliveryBaseService tbDeliveryBaseService;

    @PostMapping("/add")
    public Result add(TbDeliveryBase tbDeliveryBase) {
        tbDeliveryBaseService.save(tbDeliveryBase);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbDeliveryBaseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbDeliveryBase tbDeliveryBase) {
        tbDeliveryBaseService.update(tbDeliveryBase);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbDeliveryBase tbDeliveryBase = tbDeliveryBaseService.findById(id);
        return ResultGenerator.genSuccessResult(tbDeliveryBase);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbDeliveryBase> list = tbDeliveryBaseService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
