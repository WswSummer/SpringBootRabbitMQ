package com.wsw.rabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 10:59 2020/7/13
 * @Description:
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    Queue directQueue(){
        return new Queue("directQueue", true);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("TestDirectRouting");
    }
}
