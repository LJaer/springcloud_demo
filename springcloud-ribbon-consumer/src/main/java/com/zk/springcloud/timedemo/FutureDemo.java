package com.zk.springcloud.timedemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Future实现timeout
 */
public class FutureDemo {

    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(() -> { //正常流程
            int value = random.nextInt(200);
            System.out.println("HelloWorld()-costs " + value + " ms");
            TimeUnit.SECONDS.sleep(value);
            return "Hello World";
        });

        try{
            future.get(100,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            //超时保护
            System.out.println("超时保护！");
        }
        service.shutdown();
    }
}
