/*
package com.zk.springcloud.springcloudstream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudStreamApplicationTests {

    @Autowired
    private MessageChannel input;

    @Test
    public void contextLoads() {
        input.send(MessageBuilder.withPayload("From, SinkSender").build());
    }

}
*/
