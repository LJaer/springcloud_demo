package com.zk.springcloud.springcloudstream.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 直接调用原始API操作kafka
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //初始化配置
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        //创建 Kafka Producer
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        //创建 Kafka 消息 ProducerRecord
        String topic = "my-topic";
        Integer partition = 0;
        Long timestamp = System.currentTimeMillis();
        String key = "message-key";
        String value = "zhangsan";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, timestamp, key, value);
        //发送kafka消息
        Future<RecordMetadata> future = kafkaProducer.send(record);
        //强制执行
        future.get();
    }
}
