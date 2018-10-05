
package com.service.zipkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceZipkinController {

    @GetMapping("/service-zipkin/hello")
    public String hello() {
        return "Hello service zipkin";
    }

}
