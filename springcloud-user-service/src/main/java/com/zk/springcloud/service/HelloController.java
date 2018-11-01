package com.zk.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/toBaidu")
    void toBaidu(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.baidu.com");
    }

    @GetMapping(value = "/hello")
    public String hello() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime: " + sleepTime);
        Thread.sleep(sleepTime);
        return "Hello World 9003";
    }

    @GetMapping(value = "/getUserInfo/{id}")
    public User getUserInfo(@PathVariable String id){
        return new User("张三","123");
    }

    @GetMapping(value = "/getUserInfos/{ids}")
    public List<User> getUserInfos(@PathVariable String ids){
        System.out.println("getUserInfos---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        List<User> list = new ArrayList<>();
        list.add(new User("张三","123"));
        list.add(new User("李四","1234"));
        list.add(new User("王五","12345"));
        return list;
    }

    @GetMapping(value = "/hello1")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping(value = "/hello2")
    public User hello(@RequestHeader String name,@RequestHeader String password) {
        return new User(name,password);
    }

    @PostMapping(value = "/hello3")
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getPassword();
    }

}