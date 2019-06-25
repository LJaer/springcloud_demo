package com.zk.springcloud;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {

    private Random random = new Random();

    @GetMapping("hello-world-2")
    public String helloWorld2(){
        return new HelloWorldCommand().execute();
    }

    private class HelloWorldCommand extends HystrixCommand<String>{

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),100);
        }

        @Override
        protected String run() throws Exception {
            int value = random.nextInt(200);
            System.out.println("HelloWorld() costs " + value + " ms.");
            Thread.sleep(value);
            return "Hello,World";
        }

        //容错执行
        protected String getFallback(){
            return "error";
        }
    }
}
