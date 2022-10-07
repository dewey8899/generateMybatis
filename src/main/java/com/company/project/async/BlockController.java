package com.company.project.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther dewey
 * @date 2022/10/6 17:56
 */
@RestController
@Slf4j
public class BlockController {


    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/get")
    public ResponseMsg<String> getResult(){
        log.info("接收请求，开始处理...");
        ResponseMsg<String> result =  taskService.getResult();
        log.info("接收任务线程完成并退出");

        return result;

    }
}