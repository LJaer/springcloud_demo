package com.zk.springcloud.feign;

import com.zk.springcloud.HelloService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient(value = "SERIVCE-USER")
@Service
public interface RefactorHelloService extends HelloService {

}
