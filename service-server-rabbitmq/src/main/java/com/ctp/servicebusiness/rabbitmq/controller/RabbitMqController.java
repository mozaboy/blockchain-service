/*
 * module: Java-backend
 * file: RabbitMqController.java
 * date: 18-7-9 上午11:15
 */

/**
 * @author juhongtao
 * @create 2018-07-09 10:52
 * @desc rabbitmq service controller
 **/
package com.ctp.servicebusiness.rabbitmq.controller;

import com.ctp.servicebusiness.rabbitmq.config.RabbitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RabbitMqController {

    private Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitProperties rabbitMqConfig;

    @PostMapping("/message/send")
    public void sendMsg(@RequestParam("routingKey") String routingKey, @RequestParam("content") String content) {
        try {
            rabbitTemplate.convertAndSend(rabbitMqConfig.getExchange(),routingKey,content);
            logger.info("msg send success routingKey[" + routingKey + "]" + " content[" + content + "]" );
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

