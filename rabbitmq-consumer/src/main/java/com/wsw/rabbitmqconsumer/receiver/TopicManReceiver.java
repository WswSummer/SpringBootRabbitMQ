package com.wsw.rabbitmqconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 13:57 2020/7/13
 * @Description:
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {
    @RabbitHandler
    public void process(Map message){
        System.out.println("TopicManReceiver消费者接收到了消息：" + message.toString());
    }
}
