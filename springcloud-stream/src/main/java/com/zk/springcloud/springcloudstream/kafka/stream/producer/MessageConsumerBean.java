package com.zk.springcloud.springcloudstream.kafka.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableBinding({Sink.class})
public class MessageConsumerBean {

    @Autowired
    @Qualifier(Sink.INPUT) // Bean 名称
    SubscribableChannel subscribableChannel;

    //当字段注入完成后的回调
    @PostConstruct
    public void init(){
        //实现异步回调
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("subscribe : "+message.getPayload());
            }
        });
    }

}
