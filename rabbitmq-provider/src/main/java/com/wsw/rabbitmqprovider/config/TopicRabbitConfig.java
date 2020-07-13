package com.wsw.rabbitmqprovider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 10:59 2020/7/13
 * @Description: 交换器类型：Topic
 */
//@Configuration
public class TopicRabbitConfig {
    //绑定键值
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    //队列1
    @Bean
    Queue firstQueue(){
        return new Queue(TopicRabbitConfig.man);
    }

    //队列2
    @Bean
    Queue secondQueue(){
        return new Queue(TopicRabbitConfig.woman);
    }

    //Topic交换器
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(man);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    //这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }

}
