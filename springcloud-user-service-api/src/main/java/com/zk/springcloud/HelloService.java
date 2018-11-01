package com.zk.springcloud;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/refactor")
public interface HelloService {

    @GetMapping(value = "/hello4")
    String hello(@RequestParam("name") String name);

    @GetMapping(value = "/hello5")
    User hello(@RequestHeader("name") String name,@RequestHeader("password") String password);

    @PostMapping(value = "/hello6")
    String hello(@RequestBody User user);
}
