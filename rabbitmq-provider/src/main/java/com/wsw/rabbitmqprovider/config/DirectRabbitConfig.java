package com.wsw.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 10:59 2020/7/13
 * @Description: 交换器类型：Direct
 */
@Configuration
public class DirectRabbitConfig {
    //队列
    @Bean
    Queue directQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("directQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("directQueue", true);
    }

    //Direct交换器
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange", true, false);
    }

    //绑定---将队列和交换器绑定，并设置绑定键TestDirectRouting
    @Bean
    Binding bindingExchange(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}