package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.TbUserMapper;
import com.company.project.model.TbTest;
import com.company.project.model.TbUser;
import com.company.project.service.TbTestService;
import com.company.project.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
* Created by dewey on 2020/03/13
*/
@RestController
@RequestMapping("/tb/test")
public class TbTestController {
    @Autowired
    private TbTestService tbTestService;
    @Autowired
    private TbUserService userService;

    @PostMapping("/add")
    public Result add(TbTest tbTest) {
        tbTestService.save(tbTest);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tbTestService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/dewey")
    public Result dewey(@RequestParam Integer id) throws InterruptedException, ExecutionException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("userList");
        Future<List<TbUser>> listFuture = userService.userList(id);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        while (true){
            if (listFuture.isDone()){
                return new Result<>().setData(listFuture.get());
            }
        }

    }

    @PostMapping("/update")
    public Result update(TbTest tbTest) {
        tbTestService.update(tbTest);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        TbTest tbTest = tbTestService.findById(id);
        return ResultGenerator.genSuccessResult(tbTest);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        PageHelper.startPage(page, size);
        List<TbTest> list = tbTestService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
