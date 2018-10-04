/*
 * module: Java-backend
 * file: RabbitMqConfig.java
 * date: 18-7-5 上午11:49
 */

/**
 * @author juhongtao
 * @create 2018-06-29 11:07
 * @desc config rabbit mq
 **/
package com.ctp.servicebusiness.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Configuration
public class RabbitMqConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);
    @Autowired
    private RabbitProperties rabbitProperties;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(rabbitProperties.getAddresses());
        cachingConnectionFactory.setUsername(rabbitProperties.getUserName());
        cachingConnectionFactory.setPassword(rabbitProperties.getPassword());
        cachingConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        return cachingConnectionFactory;
    }

    @Bean
    public List<Queue> queue() {
        List<Queue> queuesList = null;
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        String[] queues = rabbitProperties.getKey().split(",");
        List<String> queueList = Arrays.asList(queues);
        queuesList = queueList.stream().map(new Function<String,Queue>(){
            @Override
            public Queue apply(String name){
               return new Queue(name,durable,exclusive,autoDelete);
            }
        }).collect(toList());

        return queuesList;
    }

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(rabbitProperties.getExchange(),durable,autoDelete);
    }

    @Bean
    public List<Binding> binding() {
        List<Queue> queueList = queue();
        List<Binding> bindingList = new ArrayList<>(queueList.size());

        if (rabbitProperties.getKey() == null || "".equals(rabbitProperties.getKey())) {
            logger.error("rabbitmq配置错误,缺少对应的routingKey.");
            return null;
        }

        String[] keys = rabbitProperties.getKey().split(",");
        List<String> keyList = Arrays.asList(keys);

        for (int i=0;i<queueList.size();i++) {

            if (queueList.get(i) == null || "".equals(queueList.get(i))) continue;
            bindingList.add(BindingBuilder.bind(queueList.get(i)).to(defaultExchange()).with(keyList.get(i)));
        }
        return bindingList;
    }
}

