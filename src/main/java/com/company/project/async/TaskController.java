package com.company.project.async;

import com.company.project.async.task.TaskQueue;
import com.company.project.async.task.TaskSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @auther dewey
 * @date 2022/10/6 17:56
 */
@RestController
@Slf4j
public class TaskController {
    //超时结果
    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1, "超时", "out of time");
    //超时时间
    private static final long OUT_OF_TIME = 5000L;
    private final TaskService taskService;

    private final TaskQueue taskQueue;

    private TaskSet taskSet;

    private int i;

    @Autowired
    public TaskController(TaskService taskService, TaskQueue taskQueue, TaskSet taskSet) {
        this.taskService = taskService;
        this.taskQueue = taskQueue;
        this.taskSet = taskSet;
    }

    @GetMapping(value = "/task_get")
    public Callable<ResponseMsg<String>> getResult() {
        log.info("接收请求，开始处理...");
        Callable<ResponseMsg<String>> result = (() -> taskService.getResult());
        log.info("接收任务线程完成并退出");
        return result;
    }

    @GetMapping(value = "/get_deferred_result")
    public DeferredResult<ResponseMsg<String>> getDeferredResult() {
        log.info("接收请求，开始处理...");
        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);
        result.onTimeout(() -> log.info("调用超时"));
        result.onCompletion(() -> log.info("调用完成"));
        //并发，加锁
        synchronized (taskQueue) {
            taskQueue.put(result);
        }
        log.info("接收任务线程完成并退出");
        return result;
    }







    @GetMapping(value = "/getDeferredResult")
    public DeferredResult<ResponseMsg<String>> get() {
        log.info("接收请求，开始处理第 {} 次请求...",++i);
        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);
        result.onTimeout(() -> {
            log.info("调用超时，移除任务，此时队列长度为{}", taskSet.getSet().size());
            synchronized (taskSet.getSet()) {
                taskSet.getSet().remove(result);
            }
        });
        result.onCompletion(() -> {
            log.info("调用完成，移除任务，此时队列长度为{}", taskSet.getSet().size());
            synchronized (taskSet.getSet()) {
                taskSet.getSet().remove(result);
            }
        });
        //并发，加锁
        synchronized (taskSet.getSet()) {
            taskSet.getSet().add(result);
        }
        log.info("加入任务集合，集合大小为:{}", taskSet.getSet().size());
        log.info("接收任务线程完成并退出");
        return result;
    }

    @GetMapping(value = "/set/{result}")
    public String setResult(@PathVariable("result") String result) {
        ResponseMsg<String> res = new ResponseMsg<>(0, "success", result);
        log.info("结果处理开始，得到输入结果为:{}", res);
        Set<DeferredResult<ResponseMsg<String>>> set = taskSet.getSet();
        synchronized (set) {
            set.forEach((deferredResult) -> deferredResult.setResult(res));
        }
        return "Successfully set result: " + result;
    }
}