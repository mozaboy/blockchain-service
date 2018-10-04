/*
 * module: service-mgr-zuul
 * file: ServiceZuulController
 * date: 18-5-4 上午11:54
 */

package com.ctp.servicemgr.zuul.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceZuulController
{
    @GetMapping("/zuul-service")
    public ResponseEntity<String> hello()
    {
        return new ResponseEntity<>("hello zuul-service", HttpStatus.OK);
    }
}
