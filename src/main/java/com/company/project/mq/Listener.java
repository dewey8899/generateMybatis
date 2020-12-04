package com.company.project.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dewey.du
 * Date on 2020-11-30 11:25:46
 */
@Component
public class Listener {
    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = "spring.dewey.queue", durable = "false"),
                  exchange = @Exchange(value = "spring.dewey", type = ExchangeTypes.TOPIC),key = "insert"
    ))
    public void listen(String msg){
        System.out.println("消费者接受到消息：" + msg);
    }
}
