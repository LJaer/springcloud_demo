package com.zk.springcloud.zuul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/local/hello")
    public String hello(){
        return "Hello World Local";
    }

}
