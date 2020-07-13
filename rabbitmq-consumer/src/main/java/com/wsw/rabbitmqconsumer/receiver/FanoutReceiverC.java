package com.wsw.rabbitmqconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 11:26 2020/7/13
 * @Description: 消息接收监听类1
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {
    @RabbitHandler
    public void process(Map message){
        System.out.println("FanoutReceiverC消费者接收到了消息：" + message.toString());
    }
}
