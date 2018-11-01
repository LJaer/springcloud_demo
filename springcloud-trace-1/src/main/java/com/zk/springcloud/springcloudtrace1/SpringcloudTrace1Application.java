package com.zk.springcloud.springcloudtrace1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class SpringcloudTrace1Application {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @GetMapping(value = "/trace-1")
    public String trace(){
        System.out.println("===call trace-1===");
        return restTemplate().getForEntity("http://trace-2/trace-2",String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTrace1Application.class, args);
    }
}
