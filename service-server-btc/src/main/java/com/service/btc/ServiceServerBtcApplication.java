package com.service.btc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceServerBtcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceServerBtcApplication.class, args);
    }
}
