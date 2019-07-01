package com.zk.springcloud.springcloudstream.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){
        System.out.println("Kafka-my-topic 消费者监听器，接收到消息：" + message);
    }

    @KafkaListener(topics = "test1")
    public void onMessage2(String message){
        System.out.println("Kafka-test 消费者监听器，接收到消息：" + message);
    }

}
