package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbReportOemProductDifference;
import com.company.project.service.TbReportOemProductDifferenceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by dewey on 2020/03/13
*/
@RestController
@RequestMapping("/tb/report/oem/product/difference")
public class TbReportOemProductDifferenceController {
    @Autowired
    private TbReportOemProductDifferenceService tbReportOemProductDifferenceService;

    @PostMapping("/add")
    public Result add(TbReportOemProductDifference tbReportOemProductDifference) {
        tbReportOemProductDifferenceService.save(tbReportOemProductDifference);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbReportOemProductDifferenceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbReportOemProductDifference tbReportOemProductDifference) {
        tbReportOemProductDifferenceService.update(tbReportOemProductDifference);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbReportOemProductDifference tbReportOemProductDifference = tbReportOemProductDifferenceService.findById(id);
        return ResultGenerator.genSuccessResult(tbReportOemProductDifference);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbReportOemProductDifference> list = tbReportOemProductDifferenceService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
