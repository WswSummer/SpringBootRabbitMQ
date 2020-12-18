package com.wsw.rabbitmqprovider.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 15:05 2020/12/8
 * @Description:
 */
@Component
public class MessageListener {
    @RabbitHandler
    @RabbitListener(queues = "wswQueue")
    public void reciver(Message message, Channel channel, Map<String, Object> messageMap) throws IOException {
        try {
            System.out.println("接受到的消息为: " + messageMap);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
