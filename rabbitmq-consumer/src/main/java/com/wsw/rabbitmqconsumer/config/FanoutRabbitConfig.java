package com.wsw.rabbitmqconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 10:59 2020/7/13
 * @Description: 交换器类型：Fanout
 */
@Configuration
public class FanoutRabbitConfig {
    //队列1
    @Bean
    Queue queueA(){
        return new Queue("fanout.A");
    }

    //队列2
    @Bean
    Queue queueB(){
        return new Queue("fanout.B");
    }

    //队列3
    @Bean
    Queue queueC(){
        return new Queue("fanout.C");
    }

    //Fanout交换器
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
