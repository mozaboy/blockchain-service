package com.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceServerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceServerOrderApplication.class, args);
    }
}
