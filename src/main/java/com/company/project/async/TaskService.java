package com.company.project.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @auther dewey
 * @date 2022/10/6 17:55
 */
@Service
@Slf4j
public class TaskService {

    public ResponseMsg<String> getResult(){

        log.info("任务开始执行，持续等待中...");

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("任务处理完成");
        return new ResponseMsg<>(0,"操作成功","success");
    }
}
