package com.company.project.async.task;

import com.company.project.async.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @auther dewey
 * @date 2022/10/6 18:04
 */
@Component
@Slf4j
public class TaskQueue {
    private static final int QUEUE_LENGTH = 10;
    private BlockingQueue<Task> queue = new LinkedBlockingDeque<>(QUEUE_LENGTH);
    private int taskId = 0;
    /**
     * 加入任务
     * @param deferredResult
     */
    public void put(DeferredResult<ResponseMsg<String>> deferredResult){
        taskId++;
        log.info("任务加入队列，id为：{}",taskId);
        queue.offer(new Task(taskId,deferredResult));
    }

    /**
     * 获取任务
     * @return
     * @throws InterruptedException
     */
    public Task take() throws InterruptedException {
        Task task = queue.poll();
        log.info("获得任务:{}",task);
        return task;
    }
}
