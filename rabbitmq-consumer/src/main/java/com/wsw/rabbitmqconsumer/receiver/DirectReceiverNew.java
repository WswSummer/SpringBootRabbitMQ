package com.wsw.rabbitmqconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 11:26 2020/7/13
 * @Description: 消息接收监听类2
 */
@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiverNew {
    @RabbitHandler
    public void process(Map message){
        System.out.println("第2个消费者接收到了消息：" + message.toString());
    }
}
