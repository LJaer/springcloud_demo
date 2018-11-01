package com.zk.springcloud;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public String getUserInfo(Long id) {
        return restTemplate.exchange("http://SERIVCE-USER/getUserInfo/1", HttpMethod.GET, null, String.class).getBody();
    }

    public String getUserInfos(List<Long> ids) {
        String usr = "http://SERIVCE-USER/getUserInfos/"+ids.toString();
        String result = restTemplate.exchange(usr, HttpMethod.GET, null, String.class).getBody();
        return result;
    }

    @HystrixCollapser(batchMethod = "test11",collapserProperties = {@HystrixProperty(name ="timerDelayInMilliseconds",value = "100")})
    public Future<User> test10(Long id) {
        return null;
    }

    @HystrixCommand
    public List<User> test11(List<Long> ids) {
        String usr = "http://SERIVCE-USER/getUserInfos/"+ids.toString();
        String result = restTemplate.exchange(usr, HttpMethod.GET, null, String.class).getBody();
        List<User> users =  JSONObject.parseArray(result,User.class);
        return users;
    }
}
