package com.zk.springcloud.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.awt.SunHints;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudConfigApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        List<String> list = new ArrayList<>();

        new Thread(() -> {
            while (true){
                String str = String.valueOf(System.currentTimeMillis());
                System.out.println("生产-"+str);
                list.add(str);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if(list.size()>0){
                    System.out.println("消费-"+list.get(0));
                    list.remove(0);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        System.in.read();
    }

}
