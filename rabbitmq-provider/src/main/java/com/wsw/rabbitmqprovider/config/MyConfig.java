package com.wsw.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 14:54 2020/12/8
 * @Description:
 */
@Configuration
public class MyConfig {
    @Bean
    Queue wswQueue(){
        return new Queue("wswQueue");
    }

    @Bean
    FanoutExchange wswFanoutExchange(){
        return new FanoutExchange("wswFanoutExchange");
    }

    @Bean
    Binding myBindingExchange(){
        return BindingBuilder.bind(wswQueue()).to(wswFanoutExchange());
    }
}
