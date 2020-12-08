package com.wsw.rabbitmqprovider.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WangSongWen
 * @Date: Created in 14:56 2020/12/8
 * @Description:
 */
@Service
public class MessageService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Map<String, Object> message){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("wswFanoutExchange", "", message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息ID: " + (correlationData != null ? correlationData.getId() : null));
        if (ack) {
            System.out.println("消息发送确认成功!");
        } else {
            System.out.println("消息发送确认失败!" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message: " + new String(message.getBody(), StandardCharsets.UTF_8) + ", replyCode: " + replyCode
                + ", replyText: " + replyText + ", exchange: " + exchange + ", routingKey: " + routingKey);
    }
}
