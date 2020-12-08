package com.wsw.rabbitmqprovider.controller;

import com.alibaba.fastjson.JSON;
import com.wsw.rabbitmqprovider.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WangSongWen
 * @Date: Created in 11:09 2020/7/13
 * @Description:
 */
@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageService messageService;

    @GetMapping("/send1")
    public String sendMessageToDirect(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageDate = "Test Message: Hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map map = new HashMap();
        map.put("messageId", messageId);
        map.put("messageDate", messageDate);
        map.put("createTime", createTime);
        //将消息携带绑定键TestDirectRouting发送到交换器TestDirectExchange
        rabbitTemplate.convertAndSend("directExchange","TestDirectRouting",map);
        return "ok";
    }

    @GetMapping("/send2")
    public String sendMessageToTopic1(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageDate = "Message: MAN!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map map = new HashMap();
        map.put("messageId", messageId);
        map.put("messageDate", messageDate);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange","topic.man",map);
        return "ok";
    }

    @GetMapping("/send3")
    public String sendMessageToTopic2(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageDate = "Message: WOMAN is all";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map map = new HashMap();
        map.put("messageId", messageId);
        map.put("messageDate", messageDate);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",map);
        return "ok";
    }

    @GetMapping("/send4")
    public String sendMessageToFanout(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageDate = "Message: Test Fanout Message!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map map = new HashMap();
        map.put("messageId", messageId);
        map.put("messageDate", messageDate);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange",null, map);
        return "ok";
    }

    @GetMapping("/testMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/testMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/wsw/send")
    public void sendMessage(){
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("id", 1);
        messageMap.put("name", "wsw");
        messageMap.put("pass", "123456");
        messageService.sendMessage(messageMap);
    }
}
