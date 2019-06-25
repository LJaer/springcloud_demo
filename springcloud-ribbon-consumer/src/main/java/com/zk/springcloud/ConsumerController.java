package com.zk.springcloud;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;
    @Autowired
    UserService userService;

    @GetMapping(value = "ribbon-consumer")
    public Object helloConsumer(){
        return helloService.helloService();
        //return restTemplate.exchange("http://SERIVCE-USER/hello", HttpMethod.GET, null, String.class).getBody();
        //return restTemplate.getForEntity("http://SERIVCE-USER/hello",String.class).getBody();
    }





    @GetMapping(value = "/getUserInfo")
    public String getUserInfo(){
        return restTemplate.exchange("http://SERIVCE-USER/getUserInfo/1", HttpMethod.GET, null, String.class).getBody();
    }

    @GetMapping(value = "/getUserInfos")
    public String getUserInfos(){
        return restTemplate.exchange("http://SERIVCE-USER/getUserInfos/1,2,3", HttpMethod.GET, null, String.class).getBody();
    }

    @RequestMapping("/test7")
    public void getUserBatchTest() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        UserCollapseCommand bc1 = new UserCollapseCommand(1l);
        UserCollapseCommand bc2 = new UserCollapseCommand(2l);
        UserCollapseCommand bc3 = new UserCollapseCommand(3l);
        UserCollapseCommand bc4 = new UserCollapseCommand(4l);
        Future<User> q1 = bc1.queue();
        Future<User> q2 = bc2.queue();
        Future<User> q3 = bc3.queue();
        User user1 = q1.get();
        User user2 = q2.get();
        User user3 = q3.get();
        Thread.sleep(3000);
        Future<User> q4 = bc4.queue();
        User user4 = q4.get();
        System.out.println("user1>>>"+user1);
        System.out.println("user2>>>"+user2);
        System.out.println("user3>>>"+user3);
        System.out.println("user4>>>"+user4);
        context.close();
    }

    @RequestMapping("/test8")
    @ResponseBody
    public void test8() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<User> f1 = userService.test10(1l);
        Future<User> f2 = userService.test10(2l);
        Future<User> f3 = userService.test10(3l);
        User b1 = f1.get();
        User b2 = f2.get();
        User b3 = f3.get();
        Thread.sleep(3000);
        Future<User> f4 = userService.test10(4l);
        User b4 = f4.get();
        System.out.println("b1>>>"+b1);
        System.out.println("b2>>>"+b2);
        System.out.println("b3>>>"+b3);
        System.out.println("b4>>>"+b4);
        context.close();
    }
}
