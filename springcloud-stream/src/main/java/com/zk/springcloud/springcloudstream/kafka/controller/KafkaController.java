package com.zk.springcloud.springcloudstream.kafka.controller;

import com.zk.springcloud.springcloudstream.kafka.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final String topic;

    private final MessageProducerBean messageProducerBean;


    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${kafka.topic}") String topic, MessageProducerBean messageProducerBean) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.messageProducerBean = messageProducerBean;
    }

    @GetMapping("/kafka/message/send")
    public boolean sendMessage(@RequestParam String message){
        kafkaTemplate.send(topic,message);
        return true;
    }

    @GetMapping("/kafka/message/send2")
    public boolean sendMessage2(@RequestParam String message){
        return messageProducerBean.send(message);
    }

}
