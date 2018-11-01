package com.zk.springcloud.springcloudtrace3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringcloudTrace3Application {

    @GetMapping(value = "/trace-3")
    public String trace(HttpServletRequest request, HttpServletResponse response){
        System.out.println("====== <call trace-3> ====== traceid = " + request.getHeader("X-B3-TraceId")
                + " spanid = "+ request.getHeader("X-B3-SpanId"));
        return "Trace";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTrace3Application.class, args);
    }
}
