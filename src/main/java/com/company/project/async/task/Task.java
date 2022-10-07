package com.company.project.async.task;

import com.company.project.async.ResponseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @auther dewey
 * @date 2022/10/6 18:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int taskId;
    private DeferredResult<ResponseMsg<String>> taskResult;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskResult" + "{responseMsg=" + taskResult.getResult() + "}" +
                '}';
    }
}
