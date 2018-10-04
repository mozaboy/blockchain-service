/*
 * module: Java-backend
 * file: ServiceBusinessRabbitmqApplication.java
 * date: 18-7-5 上午11:49
 */

package com.ctp.servicebusiness.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
public class ServiceBusinessRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBusinessRabbitmqApplication.class, args);
	}
}
