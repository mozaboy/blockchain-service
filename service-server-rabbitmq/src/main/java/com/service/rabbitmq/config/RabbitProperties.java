
package com.service.rabbitmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class RabbitProperties {

    @Value("${onechainmqconf.routingKey}")
    private String key;
    @Value("${onechainmqconf.defaultExchange}")
    private String exchange;
    @Value("${spring.rabbitmq.username}")
    private String userName;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

}

