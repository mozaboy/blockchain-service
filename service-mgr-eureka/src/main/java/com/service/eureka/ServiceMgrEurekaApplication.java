package com.service.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceMgrEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMgrEurekaApplication.class, args);
    }
}
