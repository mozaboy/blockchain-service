/*
 * module: service-mgr-zipkin
 * file: ServiceMgrZipkinApplication
 * date: 18-5-4 上午11:54
 */

package com.ctp.servicemgr.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class ServiceMgrZipkinApplication
{
    private static Logger logger = LoggerFactory.getLogger(ServiceMgrZipkinApplication.class);

    public static void main(String[] args)
    {
        logger.info("ServiceMgrZipkinApplication.run");

        SpringApplication.run(ServiceMgrZipkinApplication.class, args);
    }
}
