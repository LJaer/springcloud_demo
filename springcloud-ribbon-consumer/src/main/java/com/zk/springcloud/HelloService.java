package com.zk.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        long start = System.currentTimeMillis();
        String result = restTemplate.exchange("http://SERIVCE-USER/hello", HttpMethod.GET, null, String.class).getBody();
        System.out.println("spend time :" + (System.currentTimeMillis() - start));
        return  result;
    }

    public String helloFallback(){
        return "error";
    }
}
