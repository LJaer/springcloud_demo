package com.zk.springcloud.feign;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SpringcloudFeignApplication {

/*    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudFeignApplication.class, args);
    }
}
