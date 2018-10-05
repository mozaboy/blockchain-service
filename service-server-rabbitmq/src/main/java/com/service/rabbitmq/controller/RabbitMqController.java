
package com.service.rabbitmq.controller;

import com.service.rabbitmq.config.RabbitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

