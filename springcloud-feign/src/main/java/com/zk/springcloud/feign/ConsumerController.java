package com.zk.springcloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @GetMapping(value = "/feign-consumer")
    public String helloConsumer(){
        return helloService.hello();
    }

    @GetMapping(value = "/feign-consumer2")
    public String helloConsumer2(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("DIDI")).append("\n");
        sb.append(helloService.hello("DIDI","123456")).append("\n");
        sb.append(helloService.hello(new User("DIDI","admin"))).append("\n");
        return sb.toString();
    }

    @GetMapping(value = "/feign-consumer3")
    public String helloConsumer3(){
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MIMI")).append("\n");
        sb.append(refactorHelloService.hello("MIMI","123456")).append("\n");
        sb.append(refactorHelloService.hello(new com.zk.springcloud.User("MIMI","123456"))).append("\n");
        return sb.toString();
    }
}
