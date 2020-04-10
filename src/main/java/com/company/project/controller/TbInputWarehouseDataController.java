package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TbInputWarehouseData;
import com.company.project.service.TbInputWarehouseDataService;
import com.company.project.utils.MiscUtils;
import com.company.project.utils.excel.ExcelUtils;
import com.company.project.vo.ProductBaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
* Created by dewey on 2020/04/09
*/
@RestController
@RequestMapping("/tb/input/warehouse/data")
public class TbInputWarehouseDataController {
    @Autowired
    private TbInputWarehouseDataService tbInputWarehouseDataService;

    @PostMapping("/add")
    public Result add(TbInputWarehouseData tbInputWarehouseData) {
        tbInputWarehouseDataService.save(tbInputWarehouseData);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbInputWarehouseDataService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TbInputWarehouseData tbInputWarehouseData) {
        tbInputWarehouseDataService.update(tbInputWarehouseData);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbInputWarehouseData tbInputWarehouseData = tbInputWarehouseDataService.findById(id);
        return ResultGenerator.genSuccessResult(tbInputWarehouseData);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbInputWarehouseData> list = tbInputWarehouseDataService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String,List<TbInputWarehouseData>> map = tbInputWarehouseDataService.download();
        for (Map.Entry<String, List<TbInputWarehouseData>> entry : map.entrySet()) {
            String k = entry.getKey();
            List<TbInputWarehouseData> v = entry.getValue();
            List<ProductBaseVo> list = MiscUtils.copyList(v, ProductBaseVo.class, null);
            ExcelUtils.write2007(list, ProductBaseVo.class, k, response);
        }
    }
}
