package com.zk.springcloud.springcloudstream.kafka.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({Sink.class})
public class MessageProducerBean {

    @Autowired
    //@Output(Sink.INPUT) // Bean 名称
    @Qualifier(Sink.INPUT)  //或者用这个
    private MessageChannel messageChannel;


    /**
     * 发送消息
     * @param message 消息内容
     */
    public Boolean send(String message){
        //通过消息管道发送消息
        Message<String> build = MessageBuilder.withPayload(message).build();

        return messageChannel.send(build);
        //return sink.input().send(build);

    }
}
