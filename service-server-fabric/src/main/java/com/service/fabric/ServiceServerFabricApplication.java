package com.service.fabric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceServerFabricApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceServerFabricApplication.class, args);
    }
}
