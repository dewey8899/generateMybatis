package com.company.project.async.task;

import com.company.project.async.ResponseMsg;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther dewey
 * @date 2022/10/7 20:33
 */
@Component
@Data
public class TaskSet {

    private Set<DeferredResult<ResponseMsg<String>>> set = new HashSet<>();

}
