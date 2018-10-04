/*
 * module: service-mgr-zipkin
 * file: ServiceZipkinController
 * date: 18-5-4 上午11:54
 */

package com.ctp.servicemgr.zipkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceZipkinController
{
    @GetMapping("/service-zipkin/hello")
    public String hello() {
        return "Hello service zipkin";
    }
}
