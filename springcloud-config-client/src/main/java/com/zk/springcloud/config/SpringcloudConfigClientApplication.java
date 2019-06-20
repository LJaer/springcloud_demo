package com.zk.springcloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class SpringcloudConfigClientApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringcloudConfigClientApplication.class);
    private final ContextRefresher contextRefresher;
    private final Environment environment;

    @Autowired
    public SpringcloudConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigClientApplication.class, args);
    }

    @Scheduled(fixedRate = 5*1000,initialDelay = 3*1000)
    public void autoRefresh(){
        Set<String> updatedPropertyNames = contextRefresher.refresh();
        updatedPropertyNames.forEach(propertyName ->
                log.info("[Thread-{}] 当前配置已更新，具体 Key-{}, Value-{}",
                        Thread.currentThread().getName(),
                        propertyName,
                        environment.getProperty(propertyName))
                );
    }
}
