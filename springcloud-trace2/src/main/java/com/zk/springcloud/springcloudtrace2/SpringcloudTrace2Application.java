package com.zk.springcloud.springcloudtrace2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class SpringcloudTrace2Application {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @GetMapping(value = "/trace-2")
    public String trace(){
        System.out.println("===call trace-2===");
        return restTemplate().getForEntity("http://trace-3/trace-3",String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTrace2Application.class, args);
    }
}
