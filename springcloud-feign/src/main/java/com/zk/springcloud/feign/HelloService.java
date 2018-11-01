package com.zk.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "SERIVCE-USER",fallback = HelloServiceFallBack.class)
@Service
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @GetMapping(value = "/hello1")
    String hello(@RequestParam("name") String name);

    @GetMapping(value = "/hello2")
    User hello(@RequestHeader("name") String name,@RequestHeader("password") String password);

    @PostMapping(value = "/hello3")
    String hello(@RequestBody User user);
}

