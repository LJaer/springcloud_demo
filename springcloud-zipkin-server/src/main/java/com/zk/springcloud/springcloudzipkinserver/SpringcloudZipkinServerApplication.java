package com.zk.springcloud.springcloudzipkinserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SpringcloudZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZipkinServerApplication.class, args);
    }
}
