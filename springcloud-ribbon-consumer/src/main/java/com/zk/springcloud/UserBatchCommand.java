package com.zk.springcloud;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<User>> {
    private List<Long> ids;

    @Autowired
    UserService userService;

    public UserBatchCommand(List<Long> ids, UserService userService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CollapsingGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CollapsingKey")));
        this.ids = ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run(){
        String result = userService.getUserInfos(ids);
        List<User> users =  JSONObject.parseArray(result,User.class);
        return users;
    }
}
