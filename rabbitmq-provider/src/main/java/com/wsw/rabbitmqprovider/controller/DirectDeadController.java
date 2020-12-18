package com.wsw.rabbitmqprovider.controller;

import com.wsw.rabbitmqprovider.config.DirectDeadRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WangSongWen
 * @Date: Created in 9:56 2020/12/18
 * @Description:
 */
@RestController
public class DirectDeadController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void send(@RequestParam String msg){
        rabbitTemplate.convertAndSend(DirectDeadRabbitConfig.BUSINESS_EXCHANGE_NAME, DirectDeadRabbitConfig.ROUTING_KEY, msg);
    }
}
